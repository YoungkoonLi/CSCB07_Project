

package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerAllOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference OrderDatabase;
    DatabaseReference CusDatabase;
    CusAllOrdAdapter CusAdapter;
    FirebaseUser FBCusUser;   //current logged in user
    String CusId;       //Uid for current logged in user
    String CusName;     //name for for current logged in user
    User CusUser;       //value of type User for current logged in user
    String Order_Status;
    ArrayList<Order> StoreOrder_list;

    String StoreName;   //name of the store in loop
    String LoopUserName;    //name of customer in loop

    ValueEventListener postListener;
    ValueEventListener OrderListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_all_orders);

        FBCusUser = FirebaseAuth.getInstance().getCurrentUser();
        OrderDatabase = FirebaseDatabase.getInstance().getReference("Order");
        CusId = FBCusUser.getUid();  //this is the current customer that logs in
        CusDatabase = FirebaseDatabase.getInstance().getReference().child("User " + CusId); //this is the data for current logged in user
        StoreOrder_list = new ArrayList<>();
        // set up variables

        FindCurrentUser();
        StoreData();
        //Save StoreName and Order_Status in StoreOrder_list

        recyclerView = findViewById(R.id.CusAllOrdRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CusAdapter = new CusAllOrdAdapter(StoreOrder_list, this);
        recyclerView.setAdapter(CusAdapter);
        // the above is for activity layout

        //the below is for the three buttons at the bottom of screen layout
        Button home = findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Customer_home();
            }
        });

        Button orders = findViewById(R.id.orders);
        orders.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityOrders();
            }
        });

        Button setting = findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivitySetting();
            }
        });
    }

    public void Customer_home() {
        startActivity(new Intent(this, CustomerMain.class));

    }


    public void openActivityOrders(){
        Intent intent = new Intent(this, CustomerAllOrders.class);
        startActivity(intent);
    }

    public void openActivitySetting(){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }



    private void FindCurrentUser() {
        CusDatabase.addValueEventListener(postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@Nonnull DataSnapshot dataSnapshot) {
                CusUser = dataSnapshot.getValue(User.class);    // now CusUser has current logged in user's info, and it is of type User
                if (CusUser == null) {
                    Toast.makeText(getApplicationContext(), "No Current User", Toast.LENGTH_LONG).show();
                    //deal with nullpointerexception
                } else {
                    CusName = CusUser.name;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
        //CusDatabase.addValueEventListener(postListener);
        //OrderDatabase.addValueEventListener(OrderListener);


    private void StoreData() {
        OrderDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot2){
                for (DataSnapshot Store_snapshot : dataSnapshot2.getChildren()) {
                    //this loops child "Order" to get each Store
                    for (DataSnapshot StoreOrder_snapshot: Store_snapshot.getChildren()){
                        //this loops each Store to get each StoreOrder

                        if (StoreOrder_snapshot.child("OrderInfo").getValue() == null){
                            Toast.makeText(getApplicationContext(), "No Order Yet", Toast.LENGTH_SHORT).show();
                            continue;
                        }
                        else if (StoreOrder_snapshot.child("OrderInfo").child("Customer_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Customer Name", Toast.LENGTH_SHORT).show();
                            continue;
                        }
                        else if (StoreOrder_snapshot.child("OrderInfo").child("Store_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Store Name", Toast.LENGTH_SHORT).show();
                            continue;
                            //deal with nullpointerexception
                        }

                        LoopUserName = StoreOrder_snapshot.child("OrderInfo").child("Customer_Name").getValue().toString();
                        StoreName = StoreOrder_snapshot.child("OrderInfo").child("Store_Name").getValue().toString();

                        if(LoopUserName.equals(CusName)) {
                            if (StoreOrder_snapshot.child("OrderInfo").child("Order_Status").getValue() == null) {
                                Toast.makeText(getApplicationContext(), "No Order Status", Toast.LENGTH_SHORT).show();
                                continue;
                                //deal with nullpointerexception
                            }
                            Order_Status = StoreOrder_snapshot.child("OrderInfo").child("Order_Status").getValue().toString();
                            //set Order_Status to match the current logged in user

                            Order OrdLoop = new Order("", StoreName);
                            OrdLoop.SetOrderStatus(Order_Status);
                            StoreOrder_list.add(OrdLoop);
                            //save StoreName and Order_Status in a ArrayList
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
