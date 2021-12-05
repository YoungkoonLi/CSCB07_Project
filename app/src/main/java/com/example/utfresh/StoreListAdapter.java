package com.example.utfresh;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.StoreViewHolder> {

    Context context;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> IDs = new ArrayList<>();
    //String[] test = {"Walmart", "Shoppers", "Sobeys", "Safeway", "Costco", "Save-on-foods", "Superstore", "Store", "Example", "Subway"};
    public StoreListAdapter(Context context){
        this.context = context;
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
//        holder.storeName.setText(test[position]);
        //Log.e("Test1", test[position]);
        holder.image.setImageResource(R.drawable.sample_store);
        /*
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerProductList.class);
                intent.putExtra("storeName", holder.storeName.getText());
                context.startActivity(intent);
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder{
        TextView storeName;
        ImageView image;
        ConstraintLayout rootLayout;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.store_name);
            image = itemView.findViewById(R.id.store_image);
            rootLayout = itemView.findViewById(R.id.root_layout);
        }
    }

    public void addToNames(String name){
        this.names.add(name);
    }

    public void addToIDs(String ID){
        this.IDs.add(ID);
    }

}
