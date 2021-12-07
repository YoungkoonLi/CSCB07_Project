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
import java.util.ArrayList;

public class CusAllOrdAdapter extends RecyclerView.Adapter<CusAllOrdAdapter.CusAllOrdViewHolder>{
    //this is the setup for item_cus_all_order, which is each row in RecyclerView

    private Context context;
    ArrayList<Order> list;

    public CusAllOrdAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public void SetArrayList (ArrayList<Order> list){
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public CusAllOrdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cus_all_order, parent, false);
        return new CusAllOrdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CusAllOrdViewHolder holder, int position) {
        Order order = list.get(position);
        holder.Order_Status.setText(order.getOrder_Status());
        holder.Store_Name.setText(order.getStore_Name());
    }

    @Override
    public int getItemCount() {
        return list.size();
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