package com.example.utfresh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderViewHolder> {
    Context context;
    ArrayList<String> names;
    ArrayList<String> prices;
    ArrayList<String> categories;
    public CurrentOrderAdapter(Context context){
        this.context = context;
        //Initialize to empty list. Awaiting data from firebase.
        this.names = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    @NonNull
    @Override
    public CurrentOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new CurrentOrderViewHolder(inflater.inflate(R.layout.product_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderViewHolder holder, int position) {
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
        notifyDataSetChanged();
    }

    public class CurrentOrderViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView category;
        public CurrentOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            category = itemView.findViewById(R.id.product_category);
        }
    }
}
