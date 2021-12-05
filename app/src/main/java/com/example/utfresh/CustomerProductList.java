package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerProductList extends AppCompatActivity {
    String owner;
    RecyclerView list;
    TextView name, price, category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_list);

        //load Data here.
        loadData();

        //initialize recyclerView here.
        initializeView();
    }

    private void initializeView(){
        list = findViewById(R.id.customer_product_list);
    }

    private void loadData(){
        if(getIntent().hasExtra("storeName ")){
            owner = getIntent().getExtras().getString("storeName");
        } else{
            //If the data didn't load properly return to the previous page.
            Toast.makeText(this, "Failed to load data for the store!", Toast.LENGTH_SHORT);
            this.finish();
            return;//Make sure the execution for this piece of code terminates because finish doesn't do it.
        }
    }
}