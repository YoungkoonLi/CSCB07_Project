package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewOrderDetail extends AppCompatActivity implements Serializable {

    TextView username;
    Spinner spinner_order_status;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
//    DatabaseReference databaseReference;
//    DatabaseReference store;
//    FirebaseUser user;
//    String uid;
    OrderDetailAdapter adapter;
    ArrayList<OrderData> data_set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
//        uid = user.getUid();
//        store = databaseReference.child(uid);

        username = (TextView) findViewById(R.id.username);
        spinner_order_status = (Spinner) findViewById(R.id.spinner_order_status);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
        recyclerView = (RecyclerView) findViewById(R.id.Item_List);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new OrderDetailAdapter(this);
        recyclerView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        ArrayList<OrderData> data_set = new ArrayList<>();


        if(extras != null){
            data_set = (ArrayList<OrderData>) getIntent().getSerializableExtra("Item_List");


        }

        adapter.setItems(data_set);



//        loadData();



    }

//    private void loadData() {
//        store.child("Item_List").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<OrderData> data_set = new ArrayList<>();
//                for(DataSnapshot value : snapshot.getChildren()){
//                    OrderData data = value.getValue(OrderData.class);
//                    data_set.add(data);
//                }
//                adapter.setItems(data_set);
//                adapter.notifyDataSetChanged();
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}