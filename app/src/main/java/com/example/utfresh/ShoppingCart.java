package com.example.utfresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShoppingCart extends AppCompatActivity {

    Button CheckOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        CheckOut = findViewById(R.id.Checkout);
        CheckOut.setOnClickListener(new View.OnClickListener() {
            String Checkout = "Checkout";
            @Override
            public void onClick(View v) {
                // redirect activity to activity_customer_all_orders
                Intent intent = new Intent(ShoppingCart.this, CustomerAllOrders.class);
                Toast.makeText(getApplicationContext(), Checkout, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
