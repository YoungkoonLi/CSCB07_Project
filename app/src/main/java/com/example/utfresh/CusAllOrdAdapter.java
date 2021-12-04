package com.example.utfresh;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nonnull;

public class CusAllOrdAdapter extends RecyclerView.Adapter<CusAllOrdAdapter.CusAllOrdViewHolder>{

    Context context;
    String[] Order_Status;
    String[] Store_Name;


    public CusAllOrdAdapter(Context context, String[] Order_Status, String[] Store_Name) {
        this.context = context;
        this.Order_Status = Order_Status;
        this.Store_Name = Store_Name;
    }

    @NonNull
    @Override
    public CusAllOrdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cus_all_order, parent, false);
        return new CusAllOrdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CusAllOrdViewHolder holder, int position) {
        holder.Order_Status.setText(Order_Status[position]);
        holder.Store_Name.setText(Store_Name[position]);
    }

    @Override
    public int getItemCount() {
        return Order_Status.length;
    }

    // this helps java file CustomerAllOrders set the RecyclerView
    public class CusAllOrdViewHolder extends RecyclerView.ViewHolder{

        TextView Store_Name, Order_Status;

        public CusAllOrdViewHolder(@Nonnull View itemView) {
            super(itemView);
            Store_Name = itemView.findViewById(R.id.Store_Name);
            Order_Status = itemView.findViewById(R.id.Order_Status);

        }
    }

}
