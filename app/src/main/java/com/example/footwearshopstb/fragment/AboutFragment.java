package com.example.footwearshopstb.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.footwearshopstb.R;

public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }

    WebView privacy;
    public String filename = "privacy.html";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);


        privacy = view.findViewById(R.id.web_privacy);

        privacy.loadUrl("file:///android_asset/" + filename);

        WebSettings webSettings = privacy.getSettings();
        webSettings.setJavaScriptEnabled(true);


        return view;
    }


}