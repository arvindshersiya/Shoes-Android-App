package com.example.footwearshopstb.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.model.HorizontalProductScrollModel;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.HomePageAdapter;
import com.example.footwearshopstb.adapter.SliderAdapter;
import com.example.footwearshopstb.model.HomePageModel;
import com.example.footwearshopstb.model.SliderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private ViewPager bannerslider;
    private List<SliderModel> sliderModelList;
    private int currentpage = 2;
    private Timer timer;

    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;


    ////horizontal

    private TextView horizontallayouttitle;
    private Button horizontalviewallbtn;
    private RecyclerView horizontalrecycle;
    private RecyclerView homePageRecycleView;

    private FirebaseFirestore firebaseFirestore;

    List<HomePageModel> homePageModelList;
//    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    /*ImageView not_internet;
    FrameLayout fragment;

    LinearLayout no_internet_layout;*/


//    //////
//    List<ItemModel> itemModelList;
//    ItemAdapter itemAdapter;
//    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();

     /*   not_internet =view.findViewById(R.id.not_internet_img);
        fragment =view.findViewById(R.id.hom_fragment_container);
        no_internet_layout = view.findViewById(R.id.not_internet);

*/

     /*   ConnectivityManager connectivityManager =(ConnectivityManager)getActivity().getSystemService(getContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected() == true){
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hom_fragment_container, new HomeFragment()).commit();

        }
        else {
            // Glide.with(this).load(R.drawable.no_internet).into(not_internet);
            fragment.setVisibility(View.GONE);
            no_internet_layout.setVisibility(View.VISIBLE);
        }*/


        //SLIDER
        bannerslider = view.findViewById(R.id.hom_Frag_banner_viewpager);
        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.drawable.vans_banner_one));
        sliderModelList.add(new SliderModel(R.drawable.reebok_banner_second));
        sliderModelList.add(new SliderModel(R.drawable.levis_banner_one));
        sliderModelList.add(new SliderModel(R.drawable.reebok_banner_one));
        sliderModelList.add(new SliderModel(R.drawable.vans_banner_one));
        sliderModelList.add(new SliderModel(R.drawable.reebok_banner_second));
        sliderModelList.add(new SliderModel(R.drawable.levis_banner_one));
        sliderModelList.add(new SliderModel(R.drawable.reebok_banner_one));
        //ADAPTER SET


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
                    pageLooper();
                }
            }
        };
        bannerslider.addOnPageChangeListener(onPageChangeListener);
        startBannerSlideShow();
        bannerslider.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                pageLooper();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startBannerSlideShow();
                }
                return false;
            }
        });


        /////horizontal

        horizontallayouttitle = view.findViewById(R.id.horizontal_scroll_title);

        horizontalviewallbtn = view.findViewById(R.id.horizontal_scroll_btn);

        horizontalrecycle = view.findViewById(R.id.horizontal_scroll_recycleview);


        /*horizontalProductScrollModelList = new ArrayList<>();
         *//*horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_second,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.levis_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.reebok_banner_second,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.levis_banner_one,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.vnts,"Nike Air Force 1 '07","Men's Shoe","₹7,495"));
*//*
        HorizontalProductScrollAdapter horizontalProductScrollAdapter =new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalrecycle.setLayoutManager(linearLayoutManager);
        horizontalrecycle.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged()*/


        homePageRecycleView = view.findViewById(R.id.testing);

          /*
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,"NIKE", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,"PUMA", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,"REEBOK", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,"LEVI'S", horizontalProductScrollModelList));
*/

        LinearLayoutManager teslayout = new LinearLayoutManager(getContext());
        teslayout.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecycleView.setLayoutManager(teslayout);
        homePageModelList = new ArrayList<>();
        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        homePageRecycleView.setAdapter(adapter);


        firebaseFirestore.collection("CATEGORIES").document("HOME").collection("Deals")
                .orderBy("index")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            homePageModelList.add(new HomePageModel(0, sliderModelList));

                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                if ((long) queryDocumentSnapshot.get("view_type") == 1) {
                                    List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                                    long no_of_product = (long) queryDocumentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_product + 1; x++) {
                                        horizontalProductScrollModelList.add(new HorizontalProductScrollModel
                                                (queryDocumentSnapshot.get("product_ID_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_image_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_title_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_subtitle_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_price_" + x),
                                                        queryDocumentSnapshot.get("product_des_" + x).toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(1, queryDocumentSnapshot.get("layout_title").toString(), horizontalProductScrollModelList));
                                    //  homePageModelList.add(new HomePageModel(1,queryDocumentSnapshot.get("layout_title").toString(),horizontalProductScrollModelList));
                                } else if ((long) queryDocumentSnapshot.get("view_type") == 2) {

                                    List<HorizontalProductScrollModel> horizontalProductScrollModelLists = new ArrayList<>();
                                    long no_of_product = (long) queryDocumentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_product + 1; x++) {
                                        horizontalProductScrollModelLists.add(new HorizontalProductScrollModel
                                                (queryDocumentSnapshot.get("product_ID_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_image_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_title_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_subtitle_" + x).toString(),
                                                        queryDocumentSnapshot.get("product_price_" + x),
                                                        queryDocumentSnapshot.get("product_des_" + x).toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(1, queryDocumentSnapshot.get("layout_title").toString(), horizontalProductScrollModelLists));
                                    //  homePageModelList.add(new HomePageModel(1,queryDocumentSnapshot.get("layout_title").toString(),horizontalProductScrollModelList));
                                }

                                //


                            }

                            adapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        ///grid
       /* TextView girdtitle = view.findViewById(R.id.grid_title_now);
        Button gridviewall = view.findViewById(R.id.grid_btn_now);
        GridView gridView = view.findViewById(R.id.grid_product_layoutsss);

        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));


        //*/


//        itemModelList = new ArrayList<>();
//        itemModelList.add(new ItemModel(1, R.drawable.reebok_banner_one , "Nike Air Force 1 '07","₹7,495","Men's Shoe","Air Force 1 Origins\n" +
//                "\n" +
//                "Debuting in 1982, the AF-1 was the first basketball shoe to house Nike Air, revolutionising the game while rapidly gaining traction around the world. Today, the Air Force 1 stays true to its roots with the same soft and springy cushioning that changed sneaker history.\n"));
//
//
//        recyclerView = view.findViewById(R.id.hom_gridview);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        itemAdapter = new ItemAdapter( itemModelList);
//        recyclerView.setAdapter(itemAdapter);

        return view;

    }


    private void pageLooper() {

        if (currentpage == sliderModelList.size() - 2) {
            currentpage = 2;
            bannerslider.setCurrentItem(currentpage, false);
        }
        if (currentpage == 1) {
            currentpage = sliderModelList.size() - 3;
            bannerslider.setCurrentItem(currentpage, false);
        }

    }

    private void startBannerSlideShow() {
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