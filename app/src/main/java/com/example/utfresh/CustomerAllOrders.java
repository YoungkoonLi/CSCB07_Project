

package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerAllOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference OrderDatabase;
    DatabaseReference StoreDatabase;
    DatabaseReference CusDatabase;
    CusAllOrdAdapter CusAdapter;
    FirebaseUser FBCusUser;   //current logged in user
    String CusId;       //Uid for current logged in user
    String CusName;     //name for for current logged in user
    User CusUser;       //value of type User for current logged in user
    String Order_Status;

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
        StoreDatabase = FirebaseDatabase.getInstance().getReference("User");
        CusId = FBCusUser.getUid();  //this is the current customer that logs in
        CusDatabase = FirebaseDatabase.getInstance().getReference().child("User " + CusId); //this is the data for current logged in user
        // set up variables

        recyclerView = findViewById(R.id.CusAllOrdRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //CusAdapter = new CusAllOrdAdapter(this);
        recyclerView.setAdapter(CusAdapter);
        // tha above is for activity layout

        postListener = new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                CusUser = dataSnapshot.getValue(User.class);    // now CusUser has current logged in user's info, and it is of type User
                if (CusUser == null) {
                    Toast.makeText(getApplicationContext(), "No Current User", Toast.LENGTH_LONG).show();
                    //deal with nullpointerexception
                }
                CusName = CusUser.name;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        CusDatabase.addValueEventListener(postListener);

        OrderListener = new ValueEventListener() {
            @Override
            public void onDataChange (com.google.firebase.database.DataSnapshot dataSnapshot2){
                for (com.google.firebase.database.DataSnapshot Store_snapshot : dataSnapshot2.getChildren()) {
                    //this loops child "Order" to get each Store
                    for (com.google.firebase.database.DataSnapshot StoreOrder_snapshot: dataSnapshot2.getChildren()){
                        //this loops each Store to get each StoreOrder

                        if (StoreOrder_snapshot.child("OrderInfo").getValue() == null){
                            Toast.makeText(getApplicationContext(), "No Order Yet", Toast.LENGTH_LONG).show();
                            break;
                        }
                        if (StoreOrder_snapshot.child("OrderInfo").child("Customer_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Customer Name", Toast.LENGTH_LONG).show();
                            break;
                        }
                        if (StoreOrder_snapshot.child("OrderInfo").child("Store_Name").getValue() == null) {
                            Toast.makeText(getApplicationContext(), "No Store Name", Toast.LENGTH_LONG).show();
                            break;
                            //deal with nullpointerexception
                        }

                            LoopUserName = StoreOrder_snapshot.child("OrderInfo").child("Customer_Name").getValue().toString();
                            StoreName = StoreOrder_snapshot.child("OrderInfo").child("Store_Name").getValue().toString();

                            if(LoopUserName.equals(CusName)){
                                if (StoreOrder_snapshot.child("OrderInfo").child("Order_Status").getValue() == null){
                                    Toast.makeText(getApplicationContext(), "No Order Status", Toast.LENGTH_LONG).show();
                                    break;
                                    //deal with nullpointerexception
                                }

                                Order_Status = StoreOrder_snapshot.child("OrderInfo").child("Order_Status").getValue().toString();
                            //set Order_Status to match the current logged in user




                            //need to save StoreName and Order_Status in a ArrayList





                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };


    }


    public void Setting(View view) {
        startActivity(new Intent(CustomerAllOrders.this, CustomerMain.class));
    }


    public void Custermer_home(View view) {
        startActivity(new Intent(CustomerAllOrders.this, CustomerMain.class));
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
