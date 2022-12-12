package com.example.footwearshopstb.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.footwearshopstb.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }

    //  private RecyclerView cartItemRecyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

       /* cartItemRecyclerview = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerview.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.vnts,"Vens","Rs.599/-",1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.vnts,"Vens","Rs.599/-",1));
        cartItemModelList.add(new CartItemModel(0, R.drawable.vnts,"Vens","Rs.599/-",1));
        cartItemModelList.add(new CartItemModel(1, "Price (3 item )","Rs.1555/-","Rs.1555/-"));


        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemRecyclerview.setAdapter(cartAdapter );
        cartAdapter.notifyDataSetChanged();
*/
        return view;
    }
}