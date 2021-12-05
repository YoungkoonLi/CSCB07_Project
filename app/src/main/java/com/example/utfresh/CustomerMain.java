package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
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
        storeIDs = new ArrayList<>();
        storeNames = new ArrayList<>();
        /* initialize adapter and get the recyclerview */
        list = findViewById(R.id.storeList);
        storeAdapter = new StoreListAdapter(CustomerMain.this);
        loadStore(storeNames, storeIDs);
        list.setAdapter(storeAdapter);
        //Set linear layout
        list.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
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
                       Log.e("fuck",(String)child.child("name").getValue());
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