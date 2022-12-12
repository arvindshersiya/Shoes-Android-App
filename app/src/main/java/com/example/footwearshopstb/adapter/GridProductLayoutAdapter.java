package com.example.footwearshopstb.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footwearshopstb.activity.ProductDetailsActivity;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @Override
    public int getCount() {
        return horizontalProductScrollModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            ImageView productimage = view.findViewById(R.id.h_s_product_image);
            TextView producttitle = view.findViewById(R.id.h_s_product_title);
            TextView productcategory = view.findViewById(R.id.h_s_product_category);
            TextView productprice = view.findViewById(R.id.h_s_product_price);

            //9  productimage.setImageResource(horizontalProductScrollModelList.get(position).getProductimage());
            producttitle.setText(horizontalProductScrollModelList.get(position).getProducttitle());
            productcategory.setText(horizontalProductScrollModelList.get(position).getProductcategory());
            productprice.setText("" + horizontalProductScrollModelList.get(position).getProductprice());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

        } else {

            view = convertView;

        }
        return view;
    }
}
