package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    EditText email, password;
    TextView forgotpsk;

    ProgressBar progressBar;
    Button login_btn;
    FirebaseAuth firebaseAuth;


    VideoView video;
    TextView notaccunt, skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.log_email_textview);
        password = findViewById(R.id.log_password_textview);
        progressBar = findViewById(R.id.log_progressBar);
        login_btn = findViewById(R.id.login_btn);
        forgotpsk = findViewById(R.id.log_forgot_psk);

        firebaseAuth = FirebaseAuth.getInstance();

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_s);
        video = findViewById(R.id.video_bg_login);

        String path = "android.resource://com.example.footwearshopstb/" + R.raw.video_bgg;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);


        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                float of = 0;
                mp.setVolume(of, of);
                mp.setLooping(true);
            }
        });


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

            skip = findViewById(R.id.log_skip_textview);
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddressManager.che_skip = 1;
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    overridePendingTransition(R.anim.from_right, R.anim.fromout_left);
                    startActivity(intent);
                    finish();

                }
            });

            notaccunt = findViewById(R.id.not_ac_textview);
            notaccunt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.from_right, R.anim.fromout_left);
                }
            });

            forgotpsk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, ForgotPskActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.from_right, R.anim.fromout_left);
                }
            });


            email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInput();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    checkInput();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            login_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkEmailandPassword();
                }
            });

        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);

    }

    private void checkInput() {

        if (!TextUtils.isEmpty(email.getText()) && email.getText().toString().matches(emailPattern)) {
            if (!TextUtils.isEmpty(password.getText())) {

                login_btn.setEnabled(true);
            } else {
                password.setError("Please enter the password letter greater then 6 ");
                login_btn.setEnabled(false);
            }
        } else {
            email.setError("Please enter the valid email");
            login_btn.setEnabled(false);
        }
    }


    private void getUserName() {


        String s = email.getText().toString();
        String[] words = s.split("@");
        String username = words[0];
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("email", s);
        AddressManager.user_Id = sharedPreferences.getString("username", "");
        AddressManager.emailx = sharedPreferences.getString("email", "");

        editor.apply();


    }

    private void checkEmailandPassword() {
        if (email.getText().toString().matches(emailPattern)) {

            if (password.length() >= 6) {
                login_btn.setEnabled(false);
                login_btn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    getUserName();
                                    SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sh.edit();
                                    editor.putBoolean("flag", true);
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    overridePendingTransition(R.anim.from_right, R.anim.fromout_left);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                                    login_btn.setEnabled(true);
                                    login_btn.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);


                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show();

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