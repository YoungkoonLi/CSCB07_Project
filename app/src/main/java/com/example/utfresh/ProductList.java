package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MyAdapter adapter;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;

    TextView store_name;
    DatabaseReference StoreDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Data");
        StoreDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");

        uid = user.getUid();

        store_name = (TextView) findViewById(R.id.store_name);

        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.product_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

        loadData();

        DisplayName();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //the logic of receiving data

                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_SHORT).show();
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
        StoreDatabaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
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


    private void loadData() {
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Data> data_set = new ArrayList<>();
                for(DataSnapshot value : snapshot.getChildren()){
                    Data data = value.getValue(Data.class);
                    data_set.add(data);
                }
                adapter.setItems(data_set);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Setting(View view) {
        startActivity(new Intent(ProductList.this, Setting.class));
    }

    public void Customer_home(View view) {
        startActivity(new Intent(ProductList.this, CustomerMain.class));
    }

    public void Customer_orders(View view) {
        startActivity(new Intent(ProductList.this, CustomerAllOrders.class));
    }
}