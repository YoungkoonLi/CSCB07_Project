package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CurrentOrder extends AppCompatActivity {
    String owner;                   //current Store Name
    String id;                      //uid for current store
    ArrayList<String> names, prices, categories, quantities;
    RecyclerView orderList;
    CurrentOrderAdapter adapter;

    String CustomerId;                  //uid for current logged in user
    DatabaseReference DataOriginRef;    //reference for the origin of everything on firebase
    DatabaseReference DataSaveRef;      //reference for the specific store to save (upload) data
    View view;                          //this is the parameter for sendOrder()
    Order_Total order_total;            //this is the data (child) ready to save (upload)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        names = new ArrayList<>();
        prices = new ArrayList<>();
        categories = new ArrayList<>();
        quantities = new ArrayList<>();
        loadData();
        orderList = findViewById(R.id.customer_product_list);
        adapter = new CurrentOrderAdapter(this);
        orderList.setAdapter(adapter);
        orderList.setLayoutManager(new LinearLayoutManager(this));

        //below sets up values for variables
        DataOriginRef = FirebaseDatabase.getInstance().getReference();
        DataSaveRef = DataOriginRef.child("Orders").child(id);
        CustomerId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //below is for the SEND ORDER button
        Button Send_Order = findViewById(R.id.SendOrder);
        Send_Order.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {






                //view need to be set!





                sendOrder(view);
            }
        });
    }

    /* Get the data from the intent and initialize corresponding fields. */
    private void loadData() {
        if (getIntent().hasExtra("store_name") && getIntent().hasExtra("store_id") &&
                getIntent().hasExtra("names") && getIntent().hasExtra("prices") &&
                getIntent().hasExtra("categories") && getIntent().hasExtra("quantities")) {
            owner = getIntent().getExtras().getString("store_name");
            id = getIntent().getExtras().getString("store_id");
            names.addAll(getIntent().getExtras().getStringArrayList("names"));
            prices.addAll(getIntent().getExtras().getStringArrayList("prices"));
            categories.addAll(getIntent().getExtras().getStringArrayList("categories"));
            quantities.addAll(getIntent().getExtras().getStringArrayList("quantities"));
        } else {
            //If the data didn't load properly return to the previous page.
            Toast.makeText(this, "Failed to load data for the cart!\n Application will terminate.", Toast.LENGTH_SHORT);
            //this.finish();
            //return;//Make sure the execution for this piece of code terminates because finish doesn't do it.
        }
    }

    public void sendOrder(View view) {

        //the code should be like ref.child("students").child("s1").setValue(student);*


        //for loop needed!




        DataSaveRef.setValue(order_total);
        //order_total need to store values and set!





    }

    public class Order_Total {
        //this class is for each order child (eg order1, order2 are both of class Order_Total) under a store

        ArrayList<OrderData> Item_List;         //Item_List child under each order under each store
        Order OrderInfo;                        //OrderInfo child under each order under each store

        public Order_Total(Order orderInfo) {
            Item_List = new ArrayList<>();
            this.OrderInfo = orderInfo;
        }

        public void SetArrayList (ArrayList<OrderData> list){
            this.Item_List.addAll(list);
        }
    }
}