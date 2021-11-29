package com.example.utfresh;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataViewHolder extends RecyclerView.ViewHolder {
    public TextView input_name, input_price, input_category;
    public DataViewHolder(@NonNull View itemView){
        super(itemView);
        input_name = itemView.findViewById(R.id.input_name);
        input_price = itemView.findViewById(R.id.input_price);
        input_category = itemView.findViewById(R.id.input_category);

    }

}
