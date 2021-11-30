package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    MyAdapter adapter;
    DAOData dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.product_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOData();
        loadData();

//        Button home = findViewById(R.id.home);
//        home.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                openActivityStoreOwnerHome();
//            }
//        });
//
//        Button orders = findViewById(R.id.orders);
//        orders.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                openActivityOrders();
//            }
//        });
//
//        Button setting = findViewById(R.id.setting);
//        setting.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                openActivitySetting();
//            }
//        });
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
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

//    public void openActivityStoreOwnerHome(){
//        Intent intent = new Intent(this, StoreOwnerHome.class);
//        startActivity(intent);
//    }
//
//
//    public void openActivityOrders(){
//        Intent intent = new Intent(this, Orders.class);
//        startActivity(intent);
//    }
//
//    public void openActivitySetting(){
//        Intent intent = new Intent(this, Setting.class);
//        startActivity(intent);
//    }


}