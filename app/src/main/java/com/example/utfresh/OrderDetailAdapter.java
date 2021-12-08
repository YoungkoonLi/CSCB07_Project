package com.example.utfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>{
    private Context context;
    private ArrayList<OrderData> list = new ArrayList<OrderData>();

    public OrderDetailAdapter(Context ctx){
        this.context = ctx;

    }


    public class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        private TextView product_name, price, quantity, category;

        public OrderDetailViewHolder(@NonNull View itemView){
            super(itemView);
            product_name = itemView.findViewById(R.id.Product_Name);
            price = itemView.findViewById(R.id.Price);
            quantity = itemView.findViewById(R.id.Quantity);
            category = itemView.findViewById(R.id.Category);

        }

    }



    public void setItems(ArrayList<OrderData> data){
        list.addAll(data);
    }
    @NonNull
    @Override
    public OrderDetailAdapter.OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_in_order, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderDetailViewHolder holder, int position) {
        OrderData data = list.get(position);
        holder.product_name.setText(data.getProduct_name());
        holder.price.setText("$" + data.getPrice());
        holder.quantity.setText(data.getQuantity());
        holder.category.setText(data.getCategory());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}