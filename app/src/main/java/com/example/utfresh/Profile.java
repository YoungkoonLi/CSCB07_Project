package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView store_name;
    TextView email;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        uid = user.getUid();

        store_name = (TextView) findViewById(R.id.Store_Name);
        email = (TextView) findViewById(R.id.Email);

        DisplayName();

    }

    private void DisplayName() {
        databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                store_name.setText(name);
                String email_address = snapshot.child("email").getValue(String.class);
                email.setText(email_address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Setting(View view) {
        startActivity(new Intent(Profile.this, Setting.class));
    }

    public void Customer_home(View view) {
        startActivity(new Intent(Profile.this, CustomerMain.class));
    }

    public void Customer_orders(View view) {
        startActivity(new Intent(Profile.this, CustomerAllOrders.class));
    }
}