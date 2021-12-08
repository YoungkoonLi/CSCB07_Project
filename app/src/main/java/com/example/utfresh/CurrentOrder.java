package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurrentOrder extends AppCompatActivity {
    String owner;                   //current Store Name
    String id;                      //uid for current store
    ArrayList<String> names, prices, categories;
    ArrayList<Integer> quantities;
    RecyclerView orderList;
    CurrentOrderAdapter adapter;
    String customerName;
    String customerId;                  //uid for current logged in user
    DatabaseReference ref;      //reference for the specific store to save (upload) data
    OrderTotal orderTotal;            //this is the data (child) ready to save (upload)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        names = new ArrayList<>();
        prices = new ArrayList<>();
        categories = new ArrayList<>();
        quantities = new ArrayList<>();
        loadData();
        orderList = findViewById(R.id.current_order_list);
        adapter = new CurrentOrderAdapter(this);
        adapter.setAllList(names, prices, categories, quantities);
        orderList.setAdapter(adapter);
        orderList.setLayoutManager(new LinearLayoutManager(this));

        //below sets up values for variables
        ref = FirebaseDatabase.getInstance().getReference().child("Order").child(id);
        customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //below is for the SEND ORDER button
        Button Send_Order = findViewById(R.id.SendOrder);
        Send_Order.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //view need to be set!
                sendOrder();
            }
        });
    }

    /* Get the data from the intent and initialize corresponding fields. */
    private void loadData() {
        if (getIntent().hasExtra("store_name") && getIntent().hasExtra("store_id") &&
                getIntent().hasExtra("product_names") && getIntent().hasExtra("prices") &&
                getIntent().hasExtra("categories") && getIntent().hasExtra("quantities")) {
            owner = getIntent().getExtras().getString("store_name");
            id = getIntent().getExtras().getString("store_id");
            //The following code add the products with a quantity of at least 1 to the parallel list.
            for(int i = 0; i < getIntent().getExtras().getStringArrayList("product_names").size(); i++){
                if(getIntent().getExtras().getIntegerArrayList("quantities").get(i) > 0){
                    names.add(getIntent().getExtras().getStringArrayList("product_names").get(i));
                    prices.add(getIntent().getExtras().getStringArrayList("prices").get(i));
                    categories.add(getIntent().getExtras().getStringArrayList("categories").get(i));
                    quantities.add(getIntent().getExtras().getIntegerArrayList("quantities").get(i));
                }
            }
        } else {
            //If the data didn't load properly return to the previous page.
            Toast.makeText(this, "Failed to load data for the cart!\n Application will terminate.", Toast.LENGTH_SHORT);
            //this.finish();
            //return;//Make sure the execution for this piece of code terminates because finish doesn't do it.
        }
    }

    public void sendOrder() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                customerName = dataSnapshot.child(customerId).child("name").getValue(String.class);
                Log.e("string", dataSnapshot.child(customerId).child("name").getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        userRef.addValueEventListener(listener);

        String orderID = ref.push().getKey();
        for (int i = 0; i < names.size(); i++) {
                ref.child(orderID).child("Item_List").child("Item" + (i + 1)).setValue(new OrderData(names.get(i), prices.get(i), categories.get(i),
                        String.valueOf(quantities.get(i))));
        }
        Log.e("name", customerName);
        ref.child(orderID).child("Order_Info").push().setValue(new Order(customerName, owner));
        Toast.makeText(this, "Order sent!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public class OrderTotal {
        //this class is for each order child (eg order1, order2 are both of class Order_Total) under a store

        ArrayList<OrderData> Item_List;         //Item_List child under each order under each store
        Order OrderInfo;                        //OrderInfo child under each order under each store

        public OrderTotal(Order orderInfo) {
            Item_List = new ArrayList<>();
            this.OrderInfo = orderInfo;
        }

        public void SetArrayList(ArrayList<OrderData> list) {
            this.Item_List.addAll(list);
        }
    }
}