package com.example.utfresh;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {

    Context context;
    ArrayList<String> names;
    ArrayList<String> IDs;
    public StoreListAdapter(Context context){
        this.context = context;
        this.names = new ArrayList<>();
        this.IDs = new ArrayList<>();
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new StoreViewHolder(inflater.inflate(R.layout.store_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.storeName.setText(names.get(position));
        holder.storeID.setText(IDs.get(position));
        holder.storeID.setVisibility(View.INVISIBLE);
        holder.image.setImageResource(R.drawable.sample_store);
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerProductList.class);
                intent.putExtra("storeName", holder.storeName.getText());
                intent.putExtra("storeID", holder.storeID.getText());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    /*The ViewHolder class for this adapter. */
    public class StoreViewHolder extends RecyclerView.ViewHolder{
        TextView storeName;
        TextView storeID;
        ImageView image;
        ConstraintLayout rootLayout;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            storeID = itemView.findViewById(R.id.store_id);
            image = itemView.findViewById(R.id.store_image);
            rootLayout = itemView.findViewById(R.id.root_layout);
        }
    }

    /*This function sets 2 arraylist field in this class to the given parameter.
     * This is not done in the constructor because data must be retrieved from firebase before
     * populating the lists.
     */
    public void setAllList(ArrayList<String> names, ArrayList<String> IDs){
        this.names.addAll(names);
        this.IDs.addAll(IDs);
        notifyDataSetChanged();
    }
}
