package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerProductList extends AppCompatActivity {
    String owner;
    String id;
    ArrayList<String> names;
    ArrayList<String> prices;
    ArrayList<String> categories;
    RecyclerView list;
    DatabaseReference ref;
    ProductListAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_product_list);
        //load Data here.
        names = new ArrayList<>();
        prices = new ArrayList<>();
        categories = new ArrayList<>();
        loadData();
        //Toast.makeText(this,id + " | " + owner, Toast.LENGTH_SHORT).show();
        initialize();
        //Toast.makeText(this,names.toString(), Toast.LENGTH_SHORT).show();
    }

    /*This method initializes the recyclerView and its corresponding adapter here.*/
    private void initialize(){
        list = findViewById(R.id.customer_product_list);
        productAdapter = new ProductListAdapter(this);
        loadProducts(names, prices, categories);
        list.setAdapter(productAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    /* Get the data from the intent and initialize corresponding fields. */
    private void loadData(){
        if(getIntent().hasExtra("storeName") && getIntent().hasExtra("storeID")){
            owner = getIntent().getExtras().getString("storeName");
            id = getIntent().getExtras().getString("storeID");
        } else{
            //If the data didn't load properly return to the previous page.
            Toast.makeText(this, "Failed to load data for the store!\n Application will terminate.", Toast.LENGTH_SHORT);
            //this.finish();
            //return;//Make sure the execution for this piece of code terminates because finish doesn't do it.
        }
    }

    //Load products from firebase one by one
    protected void loadProducts(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> categories){
        ref = FirebaseDatabase.getInstance().getReference("Data").child(id);
        readOnCallback(new FireBaseCallback(){
            @Override
            public void onCallback(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> categories) {
                productAdapter.setAllList(names, prices, categories);
            }
        });
    }

    //Custom callback for firebase and other related implementations
    private interface FireBaseCallback{
        void onCallback(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> categories);
    }
    private void readOnCallback(FireBaseCallback callback){
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "data changed");
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    names.add((String)child.child("product_name").getValue());
                    prices.add((String)child.child("price").getValue());
                    categories.add((String)child.child("category").getValue());
                }
                callback.onCallback(names, prices, categories);
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