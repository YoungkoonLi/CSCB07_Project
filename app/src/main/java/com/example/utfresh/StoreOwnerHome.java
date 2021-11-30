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

        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityStoreOwnerHome();
            }
        });

        Button orders = findViewById(R.id.orders);
        orders.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityOrders();
            }
        });

        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivitySetting();
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

    public void openActivityStoreOwnerHome(){
        Intent intent = new Intent(this, StoreOwnerHome.class);
        startActivity(intent);
    }


    public void openActivityOrders(){
        Intent intent = new Intent(this, Orders.class);
        startActivity(intent);
    }

    public void openActivitySetting(){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }
}