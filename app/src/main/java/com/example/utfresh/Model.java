package com.example.utfresh;

import androidx.annotation.NonNull;

import com.example.utfresh.Contract;
import com.example.utfresh.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class Model implements Contract.Model{
    DatabaseReference users;
    FirebaseAuth fAuth;

    public Model(){
        users = FirebaseDatabase.getInstance().getReference("Users");
        fAuth = FirebaseAuth.getInstance();
    }

    public void LogIn(String email, String password, Consumer<User> callback){
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    users.child(fAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user = snapshot.getValue(User.class);
                            callback.accept(user);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                     callback.accept(null);
                }
            }
        });
    }
}
