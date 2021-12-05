package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomerMain extends AppCompatActivity {
    RecyclerView list; // The recyclerView
    ArrayList<String> storeNames; // list of store names
    ArrayList<String> storeIDs; // list of store IDs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        loadStore();
        list = findViewById(R.id.storeList);
        //set up viewAdapter
        StoreListAdapter storeAdapter = new StoreListAdapter(this, storeNames, storeIDs);
        list.setAdapter(storeAdapter);
        //Set to linear layout
        list.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
    }

    //Load store from firebase one by one
    protected void loadStore(){
        storeIDs = new ArrayList<>();
        storeNames = new ArrayList<>();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.stores_database));
        dataRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot child : task.getResult().getChildren()){
                        storeIDs.add(child.getKey());
                    }
                }
            }
        });
        //Retrieve the names of the stores using the list of ids obtained.
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference(getResources().getString(R.string.users_database));
        userRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("User Database", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot child : task.getResult().getChildren()){
                        //check if id exists in the list
                        if(storeIDs.contains(child.getKey())){
                            storeNames.add( (String) (child.child("name").getValue()));
                        }
                    }
                }
            }
        });
    }
}