package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddNewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        final EditText input_name = findViewById(R.id.input_name);
        final EditText input_price = findViewById(R.id.input_price);
        final EditText input_category = findViewById(R.id.input_category);
        Button save_button = findViewById(R.id.save_button);
        DAOData dao = new DAOData();
        save_button.setOnClickListener(v-> {
                    Data data = new Data(input_name.getText().toString(), input_price.getText().toString(), input_category.getText().toString());
                    dao.add(data).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();

                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
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

    public void Store_home(View view) {
        startActivity(new Intent(AddNewProduct.this, StoreOwnerHome.class));
    }


    public void All_orders_store(View view) {
        startActivity(new Intent(AddNewProduct.this, Orders.class));
    }

    public void Setting(View view) {
        startActivity(new Intent(AddNewProduct.this, Setting.class));
    }
}