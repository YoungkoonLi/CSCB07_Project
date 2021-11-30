package com.example.utfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {

    Context context;
    String[] names;
    public StoreListAdapter(Context context, String[] names){
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new StoreViewHolder(inflater.inflate(R.layout.store_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.storeName.setText(names[position]);
        holder.image.setImageResource(R.drawable.sample_store);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder{
        TextView storeName;
        ImageView image;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            image = itemView.findViewById(R.id.store_image);
        }
    }

}
