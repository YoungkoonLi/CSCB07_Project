package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreOwnerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_home);

        //Function of product_list button
        Button product_list = findViewById(R.id.product_list);
        product_list.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityProductList();
            }
        });

        //Function of add_new_products button
        Button add_new = findViewById(R.id.add_new_products);
        add_new.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityAddNewProduct();
            }
        });
    }

    public void openActivityProductList(){
        Intent intent = new Intent(this, ProductList.class);
        startActivity(intent);
    }

    public void openActivityAddNewProduct(){
        Intent intent = new Intent(this, AddNewProduct.class );
        startActivity(intent);
    }
}