package com.example.utfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText editEmail,editPassword;
    RadioGroup radioGroup;
    RadioButton store, customer;
    Button login, CusSignUp, StoreSignUp;
    CheckBox remember;
    FirebaseAuth fAuth;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        radioGroup = findViewById(R.id.radioGroup);
        store = findViewById(R.id.radioStoreOwner);
        customer = findViewById(R.id.radioCustomer);
        login = findViewById(R.id.login);
        CusSignUp = findViewById(R.id.signupForCustomer);
        StoreSignUp = findViewById(R.id.signupForStore);
        remember = findViewById(R.id.checkBox);
        c = findViewById(R.id.button5);

        preferences = getSharedPreferences("b07", Context.MODE_PRIVATE);
        editor = preferences.edit();
        fAuth = FirebaseAuth.getInstance();
        Remember();

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display1(v);
            }
        });

        //initial button for CusSignUp
        CusSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerSignUp(v);
            }
        });
        //initial button for StoreSignUp
        StoreSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreSignUp(v);
            }
        });
        //radioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), "Login As " + radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
        //initial button for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLogin(v);
            }
        });
    }

    public void display1(View view){
        startActivity(new Intent(MainActivity.this, CustomerMain.class));
        //Testing text
        Toast.makeText(getApplicationContext(), "started activity.", Toast.LENGTH_LONG).show();
    }

    public void Remember(){
        boolean re = preferences.getBoolean("remember", false);
        String email = preferences.getString("email", "");
        String pass = preferences.getString("password", "");
        editEmail.setText(email);
        editPassword.setText(pass);
        remember.setChecked(re);
    }

    public void CustomerSignUp(View view){
//        Intent intent = new Intent(MainActivity.this,CustomerSignUp.class);
//        startActivity(intent);
        startActivity(new Intent(MainActivity.this, CustomerSignUp.class));
    }
    public void StoreSignUp(View view){
        startActivity(new Intent(MainActivity.this, StoreSignUp.class));
    }
    public void setLogin(View view){
        //initial input string
        String email = editEmail.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();
        //check remember me
        boolean checked = remember.isChecked();
        editor.putBoolean("remember", checked);
        if(checked){
            editor.putString("email", email);
            editor.putString("password", pass);
        }else{
            editor.putString("email", "");
            editor.putString("password", "");
        }
        editor.apply();

        //email and password can not be empty
        if(TextUtils.isEmpty(email)){
            editEmail.setError("email is required!");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            editPassword.setError("password is required");
            return;
        }
        //set restriction to email and password
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Please provide valid email!");
            return;
        }
        if(pass.length() < 6){
            editPassword.setError("Password should have at least 6 characters!");
        }

        boolean isStore = store.isChecked();
        boolean isCus = customer.isChecked();

        if(!isCus && !isStore){
            Toast.makeText(getApplicationContext(), "Please select login as Customer or StoreOwner!", Toast.LENGTH_LONG).show();
            return;
        }

        fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), "Logged successfully!", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(MainActivity.this, StoreHome.class));

                    if(isStore){
                        Toast.makeText(getApplicationContext(), "Logged successfully!", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(MainActivity.this, CustomerMain.class));
                        startActivity(new Intent(MainActivity.this, StoreOwnerHome.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Logged successfully!", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(MainActivity.this, StoreOwnerHome.class));
                        startActivity(new Intent(MainActivity.this, CustomerMain.class));

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Error! " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}