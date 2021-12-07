package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.io.Serializable;
import java.util.ArrayList;

public class Orders extends AppCompatActivity implements Serializable{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private OrderAdapter.RecyclerViewClickListener listener;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private String uid;
    private ArrayList<Order> OrderInfo;
    private ArrayList<ArrayList<OrderData>> all_list;

    private ArrayList<String> key_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        uid = user.getUid();



        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.order_list);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        adapter = new OrderAdapter(this, listener);

//        recyclerView.setAdapter(adapter);

        OrderInfo = new ArrayList<Order>();
        all_list = new ArrayList<ArrayList<OrderData>>();
        key_list = new ArrayList<>();

        loadData(OrderInfo, all_list, key_list);

        setAdapter();
        Log.e("orders", adapter.list.toString());
        Log.e("orders",key_list.toString());
        Log.e("orders", all_list.toString());


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //the logic of receiving data

                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), "Refreshed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {
        setOnClickListener();
        adapter = new OrderAdapter(this, listener);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


    }

    private void setOnClickListener() {
        listener = new OrderAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ViewOrderDetail.class);
                intent.putExtra("ItemList", (Serializable) all_list.get(position));
                intent.putExtra("Customer_Name", OrderInfo.get(position).getCustomer_Name().toString());
                intent.putExtra("Key_List", key_list.get(position));
                startActivity(intent);
            }
        };
    }


    private void loadData(ArrayList<Order> OrderInfo, ArrayList<ArrayList<OrderData>> all_list, ArrayList<String> key_list) {
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot value1 : snapshot.getChildren()) {
                    ArrayList<OrderData> Item_List = new ArrayList<>();
                    for(DataSnapshot value2 : value1.child("Item_List").getChildren()){
                        OrderData item = value2.getValue(OrderData.class);
                        Item_List.add(item);
                    }
                    all_list.add(Item_List);
                }

                for (DataSnapshot value : snapshot.getChildren()) {
                    Order data = value.child("OrderInfo").getValue(Order.class);
                    OrderInfo.add(data);
                }
                adapter.setItems(OrderInfo);
                adapter.notifyDataSetChanged();

                for(DataSnapshot key_data : snapshot.getChildren()){
                    String key = key_data.getKey();
                    key_list.add(key);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Setting(View view) {
        startActivity(new Intent(Orders.this, Setting.class));
    }

    public void Customer_home(View view) {
        startActivity(new Intent(Orders.this, CustomerMain.class));
    }

    public void Customer_orders(View view) {
        startActivity(new Intent(Orders.this, CustomerAllOrders.class));
    }


//    private void setOnClickListner() {
//        listener = new OrderAdapter.RecyclerViewClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(getApplicationContext(), ViewOrderDetail.class);
//                intent.putExtra("Item_List", (Serializable) Item_List.get(position));
//                intent.putExtra("Customer_Name", OrderInfo.get(position).getCustomer_Name().toString());
//                startActivity(intent);
//            }
//        };
//    }



}