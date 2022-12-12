package com.example.footwearshopstb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private final List<SliderModel> sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slide_layout, container, false);
//        ConstraintLayout bannercontainer = view.findViewById(R.id.banner_container);
//        bannercontainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBackgroundcolor())));
        ImageView banner = view.findViewById(R.id.banner_slide_image);
        //  banner.setImageResource(sliderModelList.get(position).getBanner());
        Glide.with(container.getContext()).load(sliderModelList.get(position).getBanner()).apply(new RequestOptions().placeholder(R.drawable.reebok_banner_one))
                .into(banner);
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }
}
