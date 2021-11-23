package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StoreSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button signup;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_sign_up);

        signup = findViewById(R.id.button);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                displayUserStore();
            }
        });

    }

    public void displayUserStore(){
        Intent intent = new Intent(StoreSignUp.this, MainActivity.class);
        startActivity(intent);
    }
}