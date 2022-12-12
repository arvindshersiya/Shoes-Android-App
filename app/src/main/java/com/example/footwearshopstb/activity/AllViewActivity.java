package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.GridProductLayoutAdapter;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AllViewActivity extends AppCompatActivity {

    Toolbar toolbar;

    GridView gridView;
    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_view);

        toolbar = findViewById(R.id.a_v_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("All Shoes");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = findViewById(R.id.a_v_gridview);


        horizontalProductScrollModelList = new ArrayList<>();
     /*   horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_second,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.levis_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_second,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.levis_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_second,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.levis_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));

*/


        GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
        gridView.setAdapter(gridProductLayoutAdapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}