package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerSignUp extends AppCompatActivity {
    Button signupCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        signupCus = findViewById(R.id.button);
        signupCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUserCus();
            }
        });
    }

    public void displayUserCus(){
        Intent intent = new Intent(CustomerSignUp.this, MainActivity.class);
        startActivity(intent);
    }
}