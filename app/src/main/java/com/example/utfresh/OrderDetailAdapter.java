package com.example.utfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    ArrayList<OrderData> list = new ArrayList<>();

    public OrderDetailAdapter(Context ctx){
        this.context = ctx;

    }


    public class OrderDetailViewHolder extends RecyclerView.ViewHolder{
        private TextView Product_Name, Price, Quantity, Category;

        public OrderDetailViewHolder(@NonNull View itemView){
            super(itemView);
            Product_Name = itemView.findViewById(R.id.Product_Name);
            Price = itemView.findViewById(R.id.Price);
            Quantity = itemView.findViewById(R.id.Quantity);
            Category = itemView.findViewById(R.id.Category);

        }

    }



    public void setItems(ArrayList<OrderData> data){
        list.addAll(data);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_in_order, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OrderDetailViewHolder viewholder = (OrderDetailViewHolder)holder;
        OrderData data = list.get(position);
        viewholder.Product_Name.setText(data.getProduct_name());
        viewholder.Price.setText(data.getPrice());
        viewholder.Quantity.setText(data.getQuantity());
        viewholder.Category.setText(data.getCategory());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}