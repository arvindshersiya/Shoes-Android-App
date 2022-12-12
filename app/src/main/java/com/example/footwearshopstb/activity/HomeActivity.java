package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.fragment.AboutFragment;
import com.example.footwearshopstb.fragment.AccountFragment;
import com.example.footwearshopstb.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;


    ImageView not_internet;
    FrameLayout fragment;

    LinearLayout no_internet_layout;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        not_internet = findViewById(R.id.not_internet_img);
        fragment = findViewById(R.id.hom_fragment_container);
        no_internet_layout = findViewById(R.id.not_internet);

        bottomNavigationView = findViewById(R.id.home_butt_nav);


        getSupportFragmentManager().beginTransaction().replace(R.id.hom_fragment_container, new HomeFragment()).commit();


        SharedPreferences get = getSharedPreferences("user", MODE_PRIVATE);
        AddressManager.user_Id = get.getString("username", "");


        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        toolbar = findViewById(R.id.hom_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedfragment = null;

            switch (item.getItemId()) {

                case R.id.navv_home_n:
                    selectedfragment = new HomeFragment();
                    break;
                case R.id.navv_acc_n:
                    selectedfragment = new AccountFragment();
                    break;
                case R.id.navv_about:
                    selectedfragment = new AboutFragment();
                    break;
            }


            getSupportFragmentManager().beginTransaction().replace(R.id.hom_fragment_container, selectedfragment).commit();
            return true;
        }
    };


//    private void setActionBar(Toolbar toolbar) {
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.top_menu_cart) {


            SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
            Boolean check = sh.getBoolean("flag", false);

            if (check) {

                Intent gotodetaill = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(gotodetaill);

            } else {

                Dialog singindialog = new Dialog(HomeActivity.this);
                singindialog.setContentView(R.layout.sign_dialog);
                singindialog.setCancelable(true);

                singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singindialog.dismiss();
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                        startActivity(intent);

                    }
                });
                dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        singindialog.dismiss();
                        Intent intent = new Intent(HomeActivity.this, SignUpActivity.class);
                        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                        startActivity(intent);

                    }
                });

                singindialog.show();


            }

            //  getSupportFragmentManager().beginTransaction().replace(R.id.hom_fragment_container, new CartFragment()).commit();
//            Intent gotodetaill = new Intent(HomeActivity.this, MyCartActivity.class);
//            startActivity(gotodetaill);

           /* Dialog singindialog  = new Dialog(HomeActivity.this);
            singindialog.setContentView(R.layout.sign_dialog);
            singindialog.setCancelable(true);

            singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
            Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

            dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    singindialog.dismiss();
                    Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                    overridePendingTransition(R.anim.from_left,R.anim.fromout_right);
                    startActivity(intent);

                }
            });
            dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    singindialog.dismiss();
                    Intent intent = new Intent(HomeActivity.this,SignUpActivity.class);
                    overridePendingTransition(R.anim.from_left,R.anim.fromout_right);
                    startActivity(intent);

                }
            });

            singindialog.show();*/

        } else if (item.getItemId() == R.id.top_menu_search) {
            Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}



