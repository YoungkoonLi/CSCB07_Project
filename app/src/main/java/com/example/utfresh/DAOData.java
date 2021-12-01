package com.example.utfresh;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOData {

    // this class is to use the firebase database: use the add method to insert product into the firebase

    private DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;


    public DAOData(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference(Data.class.getSimpleName());

    }
    public Task<Void> add(Data data)
    {
        return databaseReference.child(uid).push().setValue(data);
    }


//    private DatabaseReference databaseReference;
//    public DAOData(){
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        databaseReference = db.getReference(Data.class.getSimpleName());
//
//
//    }
//    public Task<Void> add(Data data)
//    {
//        return databaseReference.push().setValue(data);
//    }

//    public Task<Void> update(String key, HashMap<String, Object> hashMap){
//        return databaseReference.child(key).updateChildren(hashMap);
//    }
//
//    public Task<Void> remove(String key){
//        return databaseReference.child(key).removeValue();
//    }

    public Query get(){
        return databaseReference.orderByKey();
    }
}
