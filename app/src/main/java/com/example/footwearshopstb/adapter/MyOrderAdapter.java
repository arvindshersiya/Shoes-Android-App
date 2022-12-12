package com.example.footwearshopstb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footwearshopstb.activity.MyOrderDetailsActivity;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.MyOrderModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    List<MyOrderModel> myOrderModelList;

    public MyOrderAdapter(List<MyOrderModel> myOrderModelList) {
        this.myOrderModelList = myOrderModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_item_layout, parent, false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {

        String resource = myOrderModelList.get(position).getProductImage();
        String ptitle = myOrderModelList.get(position).getProductTitle();
        String pQty = myOrderModelList.get(position).getProductqty();
        String deliverStatus = myOrderModelList.get(position).getDeliverStatus();


        holder.setData(resource, ptitle, deliverStatus);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotodetail = new Intent(holder.itemView.getContext(), MyOrderDetailsActivity.class);
                gotodetail.putExtra("idd", myOrderModelList.get(holder.getAdapterPosition()));
                holder.itemView.getContext().startActivity(gotodetail);

                ((Activity) holder.itemView.getContext()).finish();

            }
        });


    }

    @Override
    public int getItemCount() {
        return myOrderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView myorder_image;
        private final TextView myorder_title;
        private final TextView myorder_delivery_status;
        private final ImageView indicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //myorder_delivery_date_status
            myorder_delivery_status = itemView.findViewById(R.id.myorder_delivery_date_status);
            myorder_title = itemView.findViewById(R.id.myorder_product_title);
            myorder_image = itemView.findViewById(R.id.myoder_product_image);
            indicator = itemView.findViewById(R.id.myorder_indicator);

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent orderdtails  = new Intent(itemView.getContext(),MyOrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderdtails);


                }
            });
*/


        }

        private void setData(String resource, String title, String deliveredDate) {
            // myorder_image.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.vnts)).into(myorder_image);
            myorder_title.setText(title);

            if (deliveredDate.equals("Cancelled")) {

                indicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.red)));

            } else {
                indicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.green)));

            }

            myorder_delivery_status.setText(deliveredDate);


        }
    }
}
