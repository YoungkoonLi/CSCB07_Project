package com.example.utfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Data> list = new ArrayList<>();
    public MyAdapter(Context ctx){
        this.context = ctx;

    }
    public void setItems(ArrayList<Data> data){
        list.addAll(data);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataViewHolder viewholder = (DataViewHolder)holder;
        Data data = list.get(position);
        viewholder.input_name.setText(data.getProduct_name());
        viewholder.input_price.setText(data.getPrice());
        viewholder.input_category.setText(data.getCategory());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}