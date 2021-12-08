package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    DatabaseReference DatabaseOrigin;
    DataSnapshot LoopDataSnapshot;
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
    //ValueEventListener OrderListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Log.d("Tss tag", "Set layout complete!");
        //Toast.makeText(this, "Set layout complete", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_all_orders);
        FBCusUser = FirebaseAuth.getInstance().getCurrentUser();
        CusId = FBCusUser.getUid();  //this is the current customer that logs in
        //Toast.makeText(this, "firebase complete", Toast.LENGTH_SHORT).show();
        DatabaseOrigin = FirebaseDatabase.getInstance().getReference();
        StoreOrder_list = new ArrayList<>();
        // set up variables


        //Toast.makeText(this, "found user", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Data stored", Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, "Set Variable Complete", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.CusAllOrdRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CusAdapter = new CusAllOrdAdapter(this);
        //Toast.makeText(this, "adpater created", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Initialize CusAdapter Complete", Toast.LENGTH_SHORT).show();

        readData(new FirebaseCallBack() {
            @Override
            public void onCallback(ArrayList<Order> list) {
                CusAdapter.SetArrayList(list);
                //Save Store Name and Order_Status in ArrayList in CusAdapter
            }
        });

        //Toast.makeText(this, "ReadData Complete", Toast.LENGTH_SHORT).show();

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



    private void readData(FirebaseCallBack firebaseCallBack) {
        ValueEventListener readDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(@Nonnull DataSnapshot dataSnapshot) {
                LoopDataSnapshot = dataSnapshot.child("Users").child(CusId);
                CusUser = LoopDataSnapshot.getValue(User.class);
                // now CusUser has current logged in user's info, and it is of type User
                //Toast.makeText(getApplicationContext(), "Curr Uid:      " + CusId, Toast.LENGTH_LONG).show();
                if (CusUser == null) {
                    //Toast.makeText(getApplicationContext(), "No Current User", Toast.LENGTH_LONG).show();
                    //deal with nullpointerexception
                } else {
                    CusName = CusUser.name;
                }
                CusAdapter.notifyDataSetChanged();

                //Toast.makeText(getApplicationContext(), "Curr Name is" + CusName, Toast.LENGTH_LONG).show();

                for (DataSnapshot Store_snapshot : dataSnapshot.child("Order").getChildren()) {
                    //this loops child "Order" to get each Store
                    for (DataSnapshot StoreOrder_snapshot: Store_snapshot.getChildren()){
                        //this loops each Store to get each StoreOrder

                        if (StoreOrder_snapshot.child("OrderInfo").getValue() == null){
                            Toast.makeText(getApplicationContext(), "No Order Yet", Toast.LENGTH_SHORT).show();
                            continue;
                        }
                        else if (StoreOrder_snapshot.child("OrderInfo").child("customer_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Customer Name", Toast.LENGTH_SHORT).show();
                            continue;
                        }
                        else if (StoreOrder_snapshot.child("OrderInfo").child("store_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Store Name", Toast.LENGTH_SHORT).show();
                            continue;
                            //deal with nullpointerexception
                        }

                        LoopUserName = StoreOrder_snapshot.child("OrderInfo").child("customer_Name").getValue().toString();
                        StoreName = StoreOrder_snapshot.child("OrderInfo").child("store_Name").getValue().toString();

                        if(LoopUserName.equals(CusName)) {
                            if (StoreOrder_snapshot.child("OrderInfo").child("order_Status").getValue() == null) {
                                Toast.makeText(getApplicationContext(), "No Order Status", Toast.LENGTH_SHORT).show();
                                continue;
                                //deal with nullpointerexception
                            }
                            Order_Status = StoreOrder_snapshot.child("OrderInfo").child("order_Status").getValue().toString();
                            //set Order_Status to match the current logged in user

                            Order OrdLoop = new Order("", StoreName);
                            //Toast.makeText(getApplicationContext(), "Current status is " + Order_Status, Toast.LENGTH_LONG).show();
                            OrdLoop.SetOrderStatus(Order_Status);
                            //Toast.makeText(getApplicationContext(), "Current status is " + OrdLoop.getOrder_Status(), Toast.LENGTH_LONG).show();
                            StoreOrder_list.add(OrdLoop);
                            //fetch StoreName and Order_Status
                            //firebaseCallBack.onCallback(StoreOrder_list);
                            //CusAdapter.notifyDataSetChanged();
                        }
                    }
                }
                CusAdapter.notifyDataSetChanged();

                firebaseCallBack.onCallback(StoreOrder_list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseOrigin.addValueEventListener(readDataListener);
    }

    private interface FirebaseCallBack{
        void onCallback(ArrayList<Order> list);
    }

}