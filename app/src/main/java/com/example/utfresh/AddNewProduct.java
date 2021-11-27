package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddNewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        EditText inout_name = findViewById(R.id.input_name);
        EditText inout_price = findViewById(R.id.input_price);
        EditText inout_category = findViewById(R.id.input_category);
        ImageButton input_image = findViewById(R.id.input_image);
        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(v->
        {

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