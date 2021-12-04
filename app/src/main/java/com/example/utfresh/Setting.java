package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

//    Button Home, Orders, Logout, Setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openActivityProfile();
            }
        });

 //       Home = findViewById(R.id.home);
 //       Orders = findViewById(R.id.orders);
   //     Logout = findViewById(R.id.logout);
     //   Setting = findViewById(R.id.setting);

      /*  Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer_Main();
            }
        });

        Orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer_AllOrders();
            }
        });

        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetting();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
*/
    }

    public void openActivityProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void Logout(View view) {
        startActivity(new Intent(Setting.this, MainActivity.class));
    }

    public void Custermer_home(View view) {
        startActivity(new Intent(Setting.this, CustomerMain.class));
    }

    public void All_orders(View view) {
        startActivity(new Intent(Setting.this, CustomerAllOrders.class));
    }
/*
    public void Setting(View view) {
        startActivity(new Intent(Setting.this, Setting.class));
    }*/
/*
    public void Customer_AllOrders() {
        startActivity(new Intent(Setting.this, CustomerAllOrders.class));
    }

    public void Customer_Main() {
        startActivity(new Intent(Setting.this, CustomerMain.class));
    }*/


 /*   public void openSetting(){
        startActivity(new Intent(Setting.this, Setting.class));
    }

    public void openLogin(){
        startActivity(new Intent(Setting.this, MainActivity.class));
    }
    */
/*
    public void Setting(View view) {
        startActivity(new Intent(Setting.this, Setting.class));
    }
*/
}
