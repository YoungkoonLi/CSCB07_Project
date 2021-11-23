package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton store;
    RadioButton cus;
    RadioGroup radiogroup;
    Button signupForStore;
    Button signupForCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store=findViewById(R.id.radioStoreOwner);
        cus=findViewById(R.id.radioCustomer);
        radiogroup=findViewById(R.id.radioGroup);
        //radioGroup
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "Login as " + radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });

        //button for signUpForStore
        signupForStore = findViewById(R.id.signupForStore);
        signupForStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser2();
            }
        });
        //button for signUpForCustomer
        signupForCustomer = findViewById(R.id.signupForCustomer);
        signupForCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUser3();
            }
        });
    }

//    public void displayUser(View view){
////        if(customer){
////            Intent intent = new Intent(MainActivity.this, CustomerLogin.class);
////            intent.putExtra();
////            startActivity(intent);
////        }
//        if(storeowner){
//            Intent intent = new Intent(MainActivity.this, StoreOwnerHome.class);
//            intent.putExtra();
//            startActivity(intent);
//        }
//    }

    public void displayUser2(){
        Intent intent = new Intent(this, StoreSignUp.class);
        startActivity(intent);
    }

    public void displayUser3(){
        Intent intent = new Intent(MainActivity.this, CustomerSignUp.class);
        startActivity(intent);
    }
}