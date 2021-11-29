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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerSignUp extends AppCompatActivity {
    Button signupCus;
    EditText editEmail,username, password, phoneNum;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        signupCus = findViewById(R.id.button);
        editEmail = findViewById(R.id.editEmail);
        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        phoneNum = findViewById(R.id.editTextPhone);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        signupCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer_SignUp();
            }
        });
    }
    public void Customer_SignUp(){
        String email = editEmail.getText().toString().trim();
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            editEmail.setError("email is required.");
            return;
        }
        if(TextUtils.isEmpty(user)){
            username.setError("username is required.");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            password.setError("username is required.");
            return;
        }

        if(password.length() < 6){
            password.setError("password must be >= 6 characters");
            return;
        }
        //register in firebase
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CustomerSignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(CustomerSignUp.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}