package com.example.footwearshopstb.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footwearshopstb.model.PlaceOrderModel;
import com.example.footwearshopstb.R;

import java.util.List;

public class PlaceOrderAdapter extends RecyclerView.Adapter {

    private final List<PlaceOrderModel> placeOrderModels;

    public PlaceOrderAdapter(List<PlaceOrderModel> placeOrderModels) {
        this.placeOrderModels = placeOrderModels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.p_o_item_activity, parent, false);

        return new PlaceOrderHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String title = placeOrderModels.get(position).getProducttitle();
        String pprice = placeOrderModels.get(position).getTotalAmount();
        String pqty = placeOrderModels.get(position).getProductquantity();
        // String getTotalam = placeOrderModels.get(position).getTotalAmount();


        ((PlaceOrderAdapter.PlaceOrderHolder) holder).setPlaceOrderDetail(title, pprice, pqty);

        int totalaml = 0;
        for (int x = 0; x < placeOrderModels.size(); x++) {

            totalaml = totalaml + Integer.parseInt(placeOrderModels.get(x).getTotalAmount());

        }

        Intent intent = new Intent("AllItemAmount");
        intent.putExtra("totalAmtplcee", totalaml);
        LocalBroadcastManager.getInstance(holder.itemView.getContext()).sendBroadcast(intent);


    }


    @Override
    public int getItemCount() {
        return placeOrderModels.size();
    }

    class PlaceOrderHolder extends RecyclerView.ViewHolder {


        TextView producttitle;
        TextView productprice;
        TextView productquantity;
        TextView productTotalamt;


        public PlaceOrderHolder(@NonNull View itemView) {
            super(itemView);
            producttitle = itemView.findViewById(R.id.p_o_shoes_name);
            productprice = itemView.findViewById(R.id.p_o_shoes_price);
            productquantity = itemView.findViewById(R.id.p_o_shoes_qty);
            // productTotalamt = itemView.findViewById(R.id.gettotalamt_txt);


        }

        private void setPlaceOrderDetail(String title, String productprices, String proqty) {
            // productimage.setImageResource(resource);

            producttitle.setText(title);
            productquantity.setText("Qty: " + proqty);
            productprice.setText("₹" + productprices);
            //    productTotalamt.setText("₹" +getTotalam);


        }


    }


}
