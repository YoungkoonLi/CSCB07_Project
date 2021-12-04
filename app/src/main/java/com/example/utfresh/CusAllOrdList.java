package com.example.utfresh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CusAllOrdList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference OrderDatabase;
    DatabaseReference StoreDatabase;
    CusAllOrdAdapter CusAdapter;
    FirebaseUser user;
    String CusId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_all_orders);

        user = FirebaseAuth.getInstance().getCurrentUser();
        OrderDatabase = FirebaseDatabase.getInstance().getReference("Order");
        StoreDatabase = FirebaseDatabase.getInstance().getReference("User");
        CusId = user.getUid();

        recyclerView = findViewById(R.id.CusAllOrdRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //CusAdapter = new CusAllOrdAdapter(this);
        recyclerView.setAdapter(CusAdapter);

    }

/*    loadData();

    private void loadData() {
        databaseReference.child(CusId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Data> data_set = new ArrayList<>();
                for(DataSnapshot value : snapshot.getChildren()){
                    Data data = value.getValue(Data.class);
                    data_set.add(data);
                }
                adapter.setItems(data_set);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
