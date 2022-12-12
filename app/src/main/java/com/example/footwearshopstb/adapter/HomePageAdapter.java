package com.example.footwearshopstb.adapter;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.footwearshopstb.activity.AllViewActivity;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.SliderModel;
import com.example.footwearshopstb.model.HomePageModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {

    private final List<HomePageModel> homePageModels;

    public HomePageAdapter(List<HomePageModel> homePageModels) {
        this.homePageModels = homePageModels;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModels.get(position).getType()) {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 2:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case HomePageModel.BANNER_SLIDER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_ads_layout, parent, false);
                return new BannerSliderViewHolder(view);

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View horizontal_product = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalProductViewHolder(horizontal_product);


            case HomePageModel.GRID_PRODUCT_VIEW:
                View grid_product = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(grid_product);

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (homePageModels.get(position).getType()) {
            case HomePageModel.BANNER_SLIDER:

                List<SliderModel> sliderModelList = homePageModels.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(sliderModelList);

                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horititle = homePageModels.get(position).getTitle();
                List<HorizontalProductScrollModel> horizontalProductScrollModelList = homePageModels.get(position).getHorizontalProductScrollModelList();
                ((HorizontalProductViewHolder) holder).setHorizontalProductLayout(horizontalProductScrollModelList, horititle);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridtitle = homePageModels.get(position).getTitle();
                List<HorizontalProductScrollModel> gridProductScrollModelList = homePageModels.get(position).getHorizontalProductScrollModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridProductScrollModelList, gridtitle);


                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return homePageModels.size();
    }

    public class BannerSliderViewHolder extends RecyclerView.ViewHolder {


        private final ViewPager bannerslider;
        private int currentpage;
        private Timer timer;

        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;

        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);

            bannerslider = itemView.findViewById(R.id.hom_Frag_banner_viewpager);


        }

        private void setBannerSliderViewPager(List<SliderModel> sliderModelList) {


            SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
            bannerslider.setAdapter(sliderAdapter);
            bannerslider.setClipToPadding(false);
            bannerslider.setPageMargin(20);
            bannerslider.setCurrentItem(currentpage);
            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    currentpage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        pageLooper(sliderModelList);
                    }
                }
            };
            bannerslider.addOnPageChangeListener(onPageChangeListener);
            startBannerSlideShow(sliderModelList);
            bannerslider.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    pageLooper(sliderModelList);
                    stopBannerSlideShow();
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderModelList);
                    }
                    return false;
                }
            });
        }

        private void pageLooper(List<SliderModel> sliderModelList) {

            if (currentpage == sliderModelList.size() - 2) {
                currentpage = 2;
                bannerslider.setCurrentItem(currentpage, false);
            }
            if (currentpage == 1) {
                currentpage = sliderModelList.size() - 3;
                bannerslider.setCurrentItem(currentpage, false);
            }

        }

        private void startBannerSlideShow(List<SliderModel> sliderModelList) {
            Handler handler = new Handler();
            Runnable update = new Runnable() {
                @Override
                public void run() {
                    if (currentpage >= sliderModelList.size()) {
                        currentpage = 1;
                    }
                    bannerslider.setCurrentItem(currentpage++, true);
                }
            };
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, DELAY_TIME, PERIOD_TIME);
        }

        private void stopBannerSlideShow() {
            timer.cancel();
        }


    }

    public class HorizontalProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView horizontallayouttitle;
        private final Button horizontalviewallbtn;
        private final RecyclerView horizontalrecycle;


        public HorizontalProductViewHolder(@NonNull View itemView) {
            super(itemView);

            horizontallayouttitle = itemView.findViewById(R.id.horizontal_scroll_title);

            horizontalviewallbtn = itemView.findViewById(R.id.horizontal_scroll_btn);

            horizontalrecycle = itemView.findViewById(R.id.horizontal_scroll_recycleview);
        }

        private void setHorizontalProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {

            horizontallayouttitle.setText(title);

            //check product list and enable or disable btn

            if (horizontalProductScrollModelList.size() > 8) {
                horizontalviewallbtn.setVisibility(View.VISIBLE);
                horizontalviewallbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent allview = new Intent(itemView.getContext(), AllViewActivity.class);
                        itemView.getContext().startActivity(allview);
                    }
                });
            } else {
                horizontalviewallbtn.setVisibility(View.INVISIBLE);

            }

            HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalrecycle.setLayoutManager(linearLayoutManager);
            horizontalrecycle.setAdapter(horizontalProductScrollAdapter);
            horizontalProductScrollAdapter.notifyDataSetChanged();
        }
    }

    public class GridProductViewHolder extends RecyclerView.ViewHolder {

        private final TextView gridtitle;
        private final Button gridviewall;
        private final GridView gridView;

        public GridProductViewHolder(@NonNull View itemView) {
            super(itemView);

            gridtitle = itemView.findViewById(R.id.grid_title_now);
            gridviewall = itemView.findViewById(R.id.grid_btn_now);
            gridView = itemView.findViewById(R.id.grid_product_layoutsss);


        }

        private void setGridProductLayout(List<HorizontalProductScrollModel> horizontalProductScrollModelList, String title) {
            gridtitle.setText(title);
            gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));


        }
    }


}
