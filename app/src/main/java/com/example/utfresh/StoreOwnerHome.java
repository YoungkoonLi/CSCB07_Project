package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreOwnerHome extends AppCompatActivity {
    TextView store_name;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_home);

        //use getCurrentUser() and getUid() to get the databasereference for the current store

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        store_name = (TextView) findViewById(R.id.store_name);

        //Method to display store name on the page

        DisplayName();


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

    private void DisplayName() {
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                store_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Method to start activity

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

    public void All_orders_store(View view) {
        startActivity(new Intent(StoreOwnerHome.this, Orders.class));
    }

    public void Setting(View view) {
        startActivity(new Intent(StoreOwnerHome.this, Setting.class));
    }
}