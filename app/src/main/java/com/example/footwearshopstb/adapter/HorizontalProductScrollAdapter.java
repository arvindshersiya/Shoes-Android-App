package com.example.footwearshopstb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footwearshopstb.activity.ProductDetailsActivity;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private final List<HorizontalProductScrollModel> horizontalProductScrollModelList;


    Context context;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder holder, int position) {

        String resource = horizontalProductScrollModelList.get(position).getProductimage();
        String title = horizontalProductScrollModelList.get(position).getProducttitle();
        String category = horizontalProductScrollModelList.get(position).getProductcategory();
        long price = (long) horizontalProductScrollModelList.get(position).getProductprice();

        holder.setProductImage(resource);
        holder.setProductTitle(title);
        holder.setProductCategory(category);
        holder.setProductPrice(price);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotodetail = new Intent(holder.itemView.getContext(), ProductDetailsActivity.class);
                gotodetail.putExtra("id", horizontalProductScrollModelList.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(gotodetail);


            }
        });


    }

    @Override
    public int getItemCount() {

        if (horizontalProductScrollModelList.size() > 8) {
            return 8;

        } else {


            return horizontalProductScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productImage;
        private final TextView productTitle;
        private final TextView productCategory;
        private final TextView productPrice;
        private TextView productDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_product_image);

            productTitle = itemView.findViewById(R.id.h_s_product_title);

            productCategory = itemView.findViewById(R.id.h_s_product_category);

            productPrice = itemView.findViewById(R.id.h_s_product_price);

            ///   viewall  = itemView.findViewById(R.id.horizontal_scroll_recycleview);


            //click listener
/*            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gotodetail = new Intent(itemView.getContext(), ProductDetailsActivity.class);

                    itemView.getContext().startActivity(gotodetail);


                }
            });*/


        }

        private void setProductImage(String resource) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.icon_img)).into(productImage);
        }

        private void setProductTitle(String title) {

            productTitle.setText(title);
        }

        private void setProductCategory(String category) {
            productCategory.setText(category);
        }

        private void setProductPrice(long price) {
            productPrice.setText("₹" + price);
        }

    }
}
