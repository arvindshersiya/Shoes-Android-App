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

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    VideoView video;
    TextView haveac, skip;
    EditText full_name, email, password, confim_password;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    String patternname = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //GET ID
        video = findViewById(R.id.video_bg_signup);
        full_name = findViewById(R.id.sig_full_name_edittext);
        email = findViewById(R.id.sig_email_edittext);
        password = findViewById(R.id.sig_password_edittext);
        confim_password = findViewById(R.id.sig_confirm_password_edittext);
        progressBar = findViewById(R.id.sig_progressBar);
        signup = findViewById(R.id.signup_btn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

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


        //EDITTEXT VALIDATION
        full_name.addTextChangedListener(new TextWatcher() {
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
        confim_password.addTextChangedListener(new TextWatcher() {
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


        haveac = findViewById(R.id.have_ac_textview);
        haveac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                startActivity(intent);
                finish();
            }
        });


        skip = findViewById(R.id.sig_skip_textview);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressManager.che_skip = 1;
                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                overridePendingTransition(R.anim.from_right, R.anim.fromout_left);
                startActivity(intent);
                finish();

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailpattern();
            }
        });


    }

    private void getUserName() {

        //  AddressManager.emailx = email.getText().toString();
        String s = email.getText().toString();
        String[] words = s.split("@");
        AddressManager.user_Id = words[0];

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);

    }


    private void checkEmailpattern() {

        if (email.getText().toString().matches(emailPattern)) {


            /*  if (full_name.getText().toString().matches(patternname)){
             */
            if (password.getText().toString().equals(confim_password.getText().toString())) {


                progressBar.setVisibility(View.VISIBLE);
                signup.setVisibility(View.INVISIBLE);
                signup.setEnabled(false);


                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    getUserName();
                                    Map<Object, String> userdata = new HashMap<>();
                                    userdata.put("full_name", full_name.getText().toString());
                                    userdata.put("email", email.getText().toString());
                                    userdata.put("password", password.getText().toString());

                                    firebaseFirestore.collection("USER")
                                            .document(AddressManager.user_Id)
                                            .collection("profile")
                                            .add(userdata).
                                            addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                                    if (task.isSuccessful()) {
                                                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                                        startActivity(intent);
                                                        overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                                                        finish();

                                                    } else {
                                                        String erro = task.getException().getMessage();
                                                        Toast.makeText(SignUpActivity.this, erro, Toast.LENGTH_SHORT).show();
                                                        signup.setEnabled(true);
                                                        signup.setVisibility(View.VISIBLE);
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            });


                                } else {
                                    String error = task.getException().getMessage();

                                    Toast.makeText(SignUpActivity.this, error, Toast.LENGTH_SHORT).show();
                                    signup.setEnabled(true);
                                }
                            }
                        });

            } else {
                confim_password.setError("Password doesn't match!");

            }

         /*   }else {

            full_name.setError("Enter the valid name!");
            }

*/

        } else {

            email.setError("Please enter the valid email");
        }
    }


    private void checkInput() {
        if (!TextUtils.isEmpty(full_name.getText()) && full_name.getText().toString().matches(patternname)) {
            if (!TextUtils.isEmpty(email.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 6) {
                    if (!TextUtils.isEmpty(confim_password.getText())) {
                        signup.setEnabled(true);
                    } else {
                        signup.setEnabled(false);

                    }
                } else {
                    password.setError("Please enter the password letter greater then 6 ");
                    signup.setEnabled(false);

                }
            } else {
                email.setError("Please enter the valid email");
                signup.setEnabled(false);

            }
        } else {
            full_name.setError("Please enter the valid name");
            signup.setEnabled(false);

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