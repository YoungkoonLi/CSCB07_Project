package com.example.utfresh;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOOrder {

    // this class is to use the firebase database: use the get method to get all the orders from
    //firebase and display in the recycler view


    private DatabaseReference databaseReference;
    public DAOOrder(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Order.class.getSimpleName());


    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
