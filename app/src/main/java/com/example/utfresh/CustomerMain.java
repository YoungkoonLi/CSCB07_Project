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
import com.google.firebase.database.ValueEventListener;

public class CustomerMain extends AppCompatActivity {
    RecyclerView list; // The recyclerView
    String[] storeName; // list of store names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        list = findViewById(R.id.storeList);
        //set up viewAdapter
        StoreListAdapter storeAdapter = new StoreListAdapter(this, storeName);
        list.setAdapter(storeAdapter);
        //Set to linear layout
        list.setLayoutManager(new LinearLayoutManager(this));

        //messages to be shown after this page is created.
        Toast.makeText(getApplicationContext(), "Set up complete!", Toast.LENGTH_LONG).show();
    }
        /*
        //Load store from firebase one by one
        protected void loadStore(){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("stores");
            ref.child("s1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("demo", "Error getting data", task.getException());
                    } else {
                        //load stores one by one.
                    }
                }
            });
        }*/
}