package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StoreSignUp extends AppCompatActivity {
    Button signup;
    EditText editUsername, editEmail, editPassword,editAddress,editPhone;
    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sign_up);

        signup = findViewById(R.id.button);
        editAddress = findViewById(R.id.editStoreAddress);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editTextPhone);
        progressBar = findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

//        if(auth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayUserStore(v);
            }
        });

    }

    public void displayUserStore(View view){
        String email = editEmail.getText().toString().trim();
        String name = editUsername.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            editEmail.setError("email is required.");
            return;
        }
        if(TextUtils.isEmpty(name)){
            editUsername.setError("username is required.");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            editPassword.setError("password is required.");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            editPassword.setError("Phone number is required.");
            return;
        }
        if(TextUtils.isEmpty(address)){
            editPassword.setError("Store address is required.");
            return;
        }

        if(editPassword.length() < 6){
            editPassword.setError("password must be >= 6 characters");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        //register in firebase
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(email,name, "store");

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(auth.getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(StoreSignUp.this, "User created!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                            else{
                                Toast.makeText(StoreSignUp.this, "Error!" + task.getException(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }else{
                    Toast.makeText(StoreSignUp.this, "Error!" + task.getException(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setLogin(View view) {
        startActivity(new Intent(StoreSignUp.this, MainActivity.class));
    }
}