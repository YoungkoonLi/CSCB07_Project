package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    OrderAdapter adapter;
    DAOOrder dao;
    OrderAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setOnClickListner();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.order_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new OrderAdapter(this, listener);
        recyclerView.setAdapter(adapter);
        dao = new DAOOrder();
        loadData();
    }

    private void setOnClickListner() {
        listener = new OrderAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ViewOrderDetail.class);
                startActivity(intent);
            }
        };
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Order> data_set = new ArrayList<>();
                for(DataSnapshot value : snapshot.getChildren()){
                    Order data = value.getValue(Order.class);
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

}