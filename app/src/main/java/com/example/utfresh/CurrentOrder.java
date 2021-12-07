package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CurrentOrder extends AppCompatActivity {
    String owner;
    String id;
    ArrayList<String> names, prices, categories, quantities;
    RecyclerView orderList;
    CurrentOrderAdapter adapter;

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
    }

    /* Get the data from the intent and initialize corresponding fields. */
    private void loadData() {
        if (getIntent().hasExtra("store_name") && getIntent().hasExtra("store_id") && getIntent().hasExtra("names") && getIntent().hasExtra("prices") && getIntent().hasExtra("categories") && getIntent().hasExtra("quantities")) {
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
    }
}