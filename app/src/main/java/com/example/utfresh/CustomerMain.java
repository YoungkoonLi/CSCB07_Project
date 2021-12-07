package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerMain extends AppCompatActivity {
    RecyclerView list; // The recyclerView
    ArrayList<String> storeNames; // list of store names
    ArrayList<String> storeIDs; // list of store IDs
    StoreListAdapter storeAdapter;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        Button orders = findViewById(R.id.orders);
        orders.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityOrders();
            }
        });
        storeIDs = new ArrayList<>();
        storeNames = new ArrayList<>();
        /* initialize adapter and get the recyclerview */
        list = findViewById(R.id.storeList);
        storeAdapter = new StoreListAdapter(CustomerMain.this);
        loadStore(storeNames, storeIDs);
        list.setAdapter(storeAdapter);
        //Set linear layout
        list.setLayoutManager(new LinearLayoutManager(this));

    }

    public void openActivityOrders(){
        Intent intent = new Intent(this, CustomerAllOrders.class);
        Log.e("testmsg", "Intent created.");
        //Toast.makeText(this, "Created Intent", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
        Log.e("Furious", "ok!");
    }

    //Load store from firebase one by one
    protected void loadStore(ArrayList<String> storeNames, ArrayList<String> storeIDs){
        ref = FirebaseDatabase.getInstance().getReference();
        readOnCallback(new FireBaseCallback() {
            @Override
            public void onCallback(ArrayList<String> storeNames, ArrayList<String> storeIDs) {
                storeAdapter.setAllList(storeNames, storeIDs);
            }
        });
    }


    public void Setting(View view) {
        startActivity(new Intent(CustomerMain.this, Setting.class));
    }

    //public void Customer_orders(View view) {
        //startActivity(new Intent(CustomerMain.this, CustomerAllOrders.class));
    //}





    //Custom callback for firebase and other related implementations
    private interface FireBaseCallback{
        void onCallback(ArrayList<String> storeNames, ArrayList<String> storeIDs);
    }

    private void readOnCallback(FireBaseCallback callback){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "data changed");
                for(DataSnapshot child:dataSnapshot.child("Data").getChildren()) {
                    storeIDs.add(child.getKey());
                }
                for(DataSnapshot child : dataSnapshot.child("Users").getChildren()){
                    if(storeIDs.contains(child.getKey())){
                       storeNames.add((String)child.child("name").getValue());

                    }
                }
                callback.onCallback(storeNames, storeIDs);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);

    }
}