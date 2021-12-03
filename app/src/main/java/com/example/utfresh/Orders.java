package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    OrderAdapter adapter;
    OrderAdapter.RecyclerViewClickListener listener;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    ArrayList<Order> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setOnClickListner();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        uid = user.getUid();

        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.order_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new OrderAdapter(this, listener);
        recyclerView.setAdapter(adapter);
        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //the logic of receiving data

                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadData() {
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Order> data_set = new ArrayList<Order>();
                for (DataSnapshot value : snapshot.getChildren()) {
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

    private void setOnClickListner() {
        listener = new OrderAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                order = new ArrayList<Order>();
                Intent intent = new Intent(getApplicationContext(), ViewOrderDetail.class);
                intent.putExtra("Item_List", order.get(position).getItem_List());
                intent.putExtra("Customer_Name", order.get(position).getCustomer_Name().toString());
                startActivity(intent);
            }
        };
    }

}