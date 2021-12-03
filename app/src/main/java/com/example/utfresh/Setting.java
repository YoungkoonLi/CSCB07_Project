package com.example.utfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {
    Button home, orders, logout, setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        home = (Button) findViewById(R.id.home);
        orders = (Button) findViewById(R.id.orders);
        logout = (Button) findViewById(R.id.logout);
        setting = (Button) findViewById(R.id.setting);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrders();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetting();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    public void openHome(){
        Intent intent = new Intent(Setting.this, CustomerMain.class);
        startActivity(intent);
    }

    public void openOrders(){
        startActivity(new Intent(Setting.this, CustomerAllOrders.class));
    }

    public void openSetting(){
        startActivity(new Intent(Setting.this, Setting.class));
    }

    public void openLogin(){
        startActivity(new Intent(Setting.this, MainActivity.class));
    }

}