package com.example.utfresh;

import androidx.annotation.NonNull;
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
    String customerId;
    DatabaseReference ref;      //reference for the specific store to save (upload) data

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
        customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Order").child(id);
        getCustomerName(new FirebaseCallback() {
            @Override
            public void onCallback(String name) {
                customerName = name;
            }
        });

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

        owner = getIntent().getExtras().getString("store_name");
        id = getIntent().getExtras().getString("store_id");
        //The following code add the products with a quantity of at least 1 to the parallel list.
        for (int i = 0; i < getIntent().getExtras().getStringArrayList("product_names").size(); i++) {
            if (getIntent().getExtras().getIntegerArrayList("quantities").get(i) > 0) {
                names.add(getIntent().getExtras().getStringArrayList("product_names").get(i));
                prices.add(getIntent().getExtras().getStringArrayList("prices").get(i));
                categories.add(getIntent().getExtras().getStringArrayList("categories").get(i));
                quantities.add(getIntent().getExtras().getIntegerArrayList("quantities").get(i));
            }
        }
    }

    private void getCustomerName(FirebaseCallback callback) {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                callback.onCallback(snapshot.child(customerId).child("name").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        userRef.addValueEventListener(listener);
    }

    private interface FirebaseCallback {
        void onCallback(String customerName);
    }

    public void sendOrder() {
        String orderID = ref.push().getKey();
        for (int i = 0; i < names.size(); i++) {
            ref.child(orderID).child("Item_List").child("Item" + (i + 1)).setValue(new OrderData(names.get(i), prices.get(i), categories.get(i),
                    String.valueOf(quantities.get(i))));
        }
        Log.e("name", customerName);
        ref.child(orderID).child("OrderInfo").setValue(new Order(customerName, owner));
        Toast.makeText(this, "Order sent!", Toast.LENGTH_SHORT).show();
        finish();
    }
}