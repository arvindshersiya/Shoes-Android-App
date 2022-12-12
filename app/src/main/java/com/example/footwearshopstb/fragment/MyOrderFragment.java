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


public class MyOrderFragment extends Fragment {


    public MyOrderFragment() {
        // Required empty public constructor
    }


    //  private RecyclerView myOrderView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);


//        myOrderView=view.findViewById(R.id.f_myorder_recycleview);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        myOrderView.setLayoutManager(layoutManager);
//
//        List<MyOrderModel> myOrderModelList = new ArrayList<>();
//
//        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
//        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
//        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
//        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
//
//
//        MyOrderAdapter myOrderAdapter  = new MyOrderAdapter(myOrderModelList);
//        myOrderView.setAdapter(myOrderAdapter);
//        myOrderAdapter.notifyDataSetChanged();

        return view;
    }
}