package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewOrderDetail extends AppCompatActivity {

    TextView username;
    Spinner spinner_order_status;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_detail);

        username = (TextView) findViewById(R.id.username);
        spinner_order_status = (Spinner) findViewById(R.id.spinner_order_status);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
        recyclerView = (RecyclerView) findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }
}