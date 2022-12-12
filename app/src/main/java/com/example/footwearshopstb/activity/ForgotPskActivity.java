package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.footwearshopstb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPskActivity extends AppCompatActivity {


    TextView go_back, send_notice;
    VideoView video;
    EditText email;
    Button rest_btn;
    ProgressBar progressBar;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psk);

        video = findViewById(R.id.video_bg_forgot_psk);
        go_back = findViewById(R.id.go_back_textview);
        email = findViewById(R.id.fog_email_textview);
        progressBar = findViewById(R.id.fog_progressBar);
        rest_btn = findViewById(R.id.rest_btn);
        send_notice = findViewById(R.id.fog_notce_textview);

        firebaseAuth = FirebaseAuth.getInstance();

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

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPskActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                finish();
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

        rest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmail();

            }
        });


    }

    private void checkEmail() {

        if (email.getText().toString().matches(emailPattern)) {

            progressBar.setVisibility(View.VISIBLE);
            rest_btn.setEnabled(false);
            rest_btn.setVisibility(View.INVISIBLE);
            firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                String error = task.getException().getMessage();
                                progressBar.setVisibility(View.INVISIBLE);
                                send_notice.setText(error);
                                rest_btn.setVisibility(View.VISIBLE);
                                rest_btn.setEnabled(true);

                            }
                            rest_btn.setVisibility(View.VISIBLE);
                            send_notice.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    });

        } else {
            Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();

        }

    }

    private void checkInput() {
        if (TextUtils.isEmpty(email.getText())) {
            rest_btn.setEnabled(false);
        } else {

            rest_btn.setEnabled(true);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
    }
}