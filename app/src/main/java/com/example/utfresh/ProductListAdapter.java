package com.example.utfresh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    Context context;
    String storeID;
    ArrayList<String> names;
    ArrayList<String> prices;
    ArrayList<String> categories;
    static int[] quantities;
    public ProductListAdapter(Context context){
        this.context = context;
        //Initialize to empty list. Awaiting data from firebase.
        this.names = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ProductViewHolder(inflater.inflate(R.layout.product_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.name.setText(names.get(position));
        holder.price.setText("$" + prices.get(position));
        holder.category.setText(categories.get(position));

    }

    @Override
    public int getItemCount() {
        Log.e("text", names.size() + "");
        return names.size();
    }

    /*This function sets all arraylist fields in this class to the given parameter
     * This is not done in the constructor because data must be retrieved from firebase before
     * populating the lists.
     */
    public void setAllList(ArrayList<String> names, ArrayList<String> prices, ArrayList<String> categories){
        this.names.addAll(names);
        this.prices.addAll(prices);
        this.categories.addAll(categories);
        this.quantities = new int[names.size()];
        //zero out the list
        for(int i = 0; i < quantities.length; i++){
            quantities[i] = 0;
        }
        notifyDataSetChanged();
    }

    public void addToCart(String name){
        int index = names.indexOf(name);
        quantities[index]++;
        Toast.makeText(context, "Added 1 " + name +"to shopping cart", Toast.LENGTH_SHORT);
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView category;
        Button add;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            category = itemView.findViewById(R.id.product_category);
            add = itemView.findViewById(R.id.add_cart);
        }
    }
}
