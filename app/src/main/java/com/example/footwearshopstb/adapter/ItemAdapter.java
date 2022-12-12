package com.example.footwearshopstb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footwearshopstb.fragment.HomeFragment;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.ItemModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MainViewHolder> {

    HomeFragment context = new HomeFragment();
    List<ItemModel> itemModelList;

    public ItemAdapter(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //     return new MainViewHolder(LayoutInflater.from(parent).inflate(R.layout.item_container_grid,parent,false));
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_grid, parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.title.setText(itemModelList.get(position).getName());
        holder.category.setText(itemModelList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        ImageView itemimage;
        TextView title, category;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            itemimage = itemView.findViewById(R.id.grid_imageview);
            title = itemView.findViewById(R.id.grid_title_textview);
            category = itemView.findViewById(R.id.grid_subtitle_textview);
        }
    }
}
