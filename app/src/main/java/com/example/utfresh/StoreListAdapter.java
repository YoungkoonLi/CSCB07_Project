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
    ArrayList<String> names;
    ArrayList<String> IDs;
    String[] test = {"Walmart", "Shoppers", "Sobeys", "Safeway", "Costco", "Save-on-foods", "Superstore", "Store", "Example", "Subway"};
    public StoreListAdapter(Context context, ArrayList<String> names, ArrayList<String> IDs){
        this.context = context;
        this.names = names;
        this.IDs = IDs;
        for(String s : names){
            Log.e("namesTest", s);
        }
        Log.e(",,", "-----------------\n");
        for(String s : IDs){
            Log.e("idsTest", s);
        }
        Log.e("", "Set up complete!");
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new StoreViewHolder(inflater.inflate(R.layout.store_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        //holder.storeName.setText(names.get(position));
        holder.storeName.setText(test[position]);
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
        return test.length;
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

}
