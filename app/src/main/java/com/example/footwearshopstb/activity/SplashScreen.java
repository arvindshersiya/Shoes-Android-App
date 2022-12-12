package com.example.footwearshopstb.activity;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;

public class SplashScreen extends AppCompatActivity {
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_s);
        video = findViewById(R.id.bg_video);

        String path = "android.resource://com.example.footwearshopstb/" + R.raw.video_bgg;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);
        video.setAnimation(anim);

        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                float of = 0;
                mp.setVolume(of, of);
                mp.setLooping(true);
            }
        });

        ImageView img = findViewById(R.id.imageView2);
        img.setAnimation(anim);
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // when click back its skip Splash Activity
                }

            }
        });thread.start();*/


        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.not_internet_layout);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            Button notInternetBtn = dialog.findViewById(R.id.not_internet_btn);
            notInternetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            dialog.show();
        } else {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                    Boolean check = sh.getBoolean("flag", false);

                    Intent next;

                    if (check) {

                        next = new Intent(getApplicationContext(), HomeActivity.class);

                    } else if (AddressManager.che_skip == 1) {
                        next = new Intent(getApplicationContext(), HomeActivity.class);
                    } else {

                        next = new Intent(getApplicationContext(), LoginActivity.class);


                    }
                    startActivity(next);
                    finish();

                }
            }, 4000);
        }
    }

    @Override
    protected void onPostResume() {
        video.resume();
        super.onPostResume();
    }

    @Override
    protected void onRestart() {
        video.start();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        video.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        video.stopPlayback();
        super.onDestroy();
    }
}