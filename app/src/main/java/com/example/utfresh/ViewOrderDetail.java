package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewOrderDetail extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener {

    TextView username;
    Spinner spinner_order_status;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    OrderDetailAdapter adapter;
    String key;
    String item;
    String status;

    String[] spinner_source = new String[]{
            "Choose an order status",
            "Pending",
            "Ready for Pick Up",
            "Complete"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

        adapter = new OrderDetailAdapter(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        uid = user.getUid();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
        recyclerView = (RecyclerView) findViewById(R.id.Item_List);

        username = (TextView) findViewById(R.id.username);
        spinner_order_status = (Spinner) findViewById(R.id.spinner_order_status);
        spinner_order_status.setOnItemSelectedListener(this);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                spinner_source);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_order_status.setAdapter(spinnerArrayAdapter);

        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.Item_List);


        ArrayList<OrderData> data_set = (ArrayList<OrderData>) getIntent().getSerializableExtra("ItemList");
        String customer_name = (String) getIntent().getStringExtra("Customer_Name");
        key = (String) getIntent().getStringExtra("Key_List");


        username.setText(customer_name);
        adapter.setItems(data_set);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);





        Button update_button = findViewById(R.id.update_button);

        update_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SaveValue(item);


            }
        });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = spinner_order_status.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void SaveValue(String item){
        if(item == "Choose an order status"){
            Toast.makeText(this, "Please choose an order status", Toast.LENGTH_SHORT).show();
        }else{
            status = item;
            databaseReference.child(uid).child(key).child("OrderInfo").child("order_Status").setValue(status);
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public void Setting(View view) {
        startActivity(new Intent(ViewOrderDetail.this, Setting.class));
    }

    public void Store_home(View view) {
        startActivity(new Intent(ViewOrderDetail.this, StoreOwnerHome.class));
    }

    public void Store_orders(View view) {
        startActivity(new Intent(ViewOrderDetail.this, AddNewProduct.class));
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