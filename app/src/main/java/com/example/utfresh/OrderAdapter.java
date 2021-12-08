package com.example.utfresh;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// this class is the adapter and View Holder for the Order page

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private Context context;
    ArrayList<Order> list = new ArrayList<>();
    private RecyclerViewClickListener listener;


    public OrderAdapter(Context ctx, RecyclerViewClickListener listener){
        this.listener = listener;
        this.context = ctx;

    }


    public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView customer_Name, order_Status, store_Name;

        public OrderViewHolder(@NonNull View itemView){
            super(itemView);
            customer_Name = itemView.findViewById(R.id.Customer_Name);
            order_Status = itemView.findViewById(R.id.Order_Status);
            store_Name = itemView.findViewById(R.id.Store_Name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());

        }
    }



    public void setItems(ArrayList<Order> data){
        list.addAll(data);
    }
    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_layout, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order data = list.get(position);
        holder.customer_Name.setText(data.getCustomer_Name());
        holder.order_Status.setText(data.getOrder_Status());
        holder.store_Name.setText(data.getStore_Name());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}