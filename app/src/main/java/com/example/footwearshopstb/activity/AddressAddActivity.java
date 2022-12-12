package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AddressAddActivity extends AppCompatActivity {


    Toolbar toolbar;
    Button save;
    EditText person_name, city, state, locality, flat, pinno;
    EditText ephone_number;
    TextView Sname;
    TextView Sphone;
    TextView Sflat;
    TextView Slocality;
    TextView Scity;
    TextView Sstate;
    TextView Spinno;

    String checknull = "null";

    Button address_continue;

    FirebaseFirestore firebaseFirestore;

    Button adsupdate;
    LinearLayout adressViewlayout;
    ScrollView scrollView;
    String patternname = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);

        toolbar = findViewById(R.id.add_manager_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Address Manager");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();

        scrollView = findViewById(R.id.sclroll_view);
        adressViewlayout = findViewById(R.id.address_view_layout);
        address_continue = findViewById(R.id.addr_continue_btn);


        Sname = findViewById(R.id.setname);
        Sphone = findViewById(R.id.setphone);
        Sflat = findViewById(R.id.setflat);
        Slocality = findViewById(R.id.setlocality);
        Scity = findViewById(R.id.setncity);
        Sstate = findViewById(R.id.setstate);
        Spinno = findViewById(R.id.setpin);


        person_name = findViewById(R.id.add_manager_personname_txte);

        ephone_number = findViewById(R.id.add_manager_phone_txte);
        city = findViewById(R.id.add_manager_city);
        state = findViewById(R.id.add_manager_state_txte);
        locality = findViewById(R.id.add_manager_locality_area_txte);
        flat = findViewById(R.id.add_manager_float_building_txte);
        pinno = findViewById(R.id.add_manager_pinnumber_txte);

        person_name.addTextChangedListener(new TextWatcher() {
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
        ephone_number.addTextChangedListener(new TextWatcher() {
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
        flat.addTextChangedListener(new TextWatcher() {
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
        locality.addTextChangedListener(new TextWatcher() {
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
        city.addTextChangedListener(new TextWatcher() {
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
        state.addTextChangedListener(new TextWatcher() {
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
        pinno.addTextChangedListener(new TextWatcher() {
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


        save = findViewById(R.id.add_manager_save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStor();
                dataLoad();
                // checkAddressAvailablr();
//                address_continue.setEnabled(true);
//                address_continue.setText("Continue");
//
                scrollView.setVisibility(View.GONE);
//            //    address_continue.setAnimation(anim_inleft);
                adressViewlayout.setVisibility(View.VISIBLE);
//            //    adsupdate.setAnimation(anim_inleft);
                adsupdate.setVisibility(View.VISIBLE);

                if (AddressManager.chekAcitycity == 0) {
                    address_continue.setText("Continue");
                    address_continue.setVisibility(View.VISIBLE);
                    address_continue.setEnabled(true);
                } else if (AddressManager.chekAcitycity == 1) {
                    address_continue.setVisibility(View.GONE);
                }

            }
        });

        adsupdate = findViewById(R.id.addres_update_btn);
        adsupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsupdate.setVisibility(View.GONE);
                adressViewlayout.setVisibility(View.GONE);
                //    scrollView.setAnimation(anim_inright);
                scrollView.setVisibility(View.VISIBLE);
                address_continue.setVisibility(View.GONE);

            }
        });

        address_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderPlaceActivity.class);
                startActivity(intent);
                finish();
            }
        });


        dataLoad();
        checkAddressAvailablr();

        checkPrevousLayout();


    }

    private void checkPrevousLayout() {


        if (AddressManager.chekAcitycity == 0) {
            address_continue.setVisibility(View.VISIBLE);
        } else if (AddressManager.chekAcitycity == 1) {
            address_continue.setVisibility(View.GONE);
        }
    }

    void checkInput() {

        if (!TextUtils.isEmpty(person_name.getText()) && person_name.getText().toString().matches(patternname)) {

            if (!TextUtils.isEmpty(ephone_number.getText()) && ephone_number.length() == 10) {
                if (!TextUtils.isEmpty(flat.getText())) {
                    if (!TextUtils.isEmpty(locality.getText())) {
                        if (!TextUtils.isEmpty(city.getText())) {
                            if (!TextUtils.isEmpty(state.getText())) {
                                if (!TextUtils.isEmpty(pinno.getText()) && pinno.length() == 6) {
                                    save.setEnabled(true);
                                } else {

                                    pinno.setError("Please enter the valid pin number");
                                    save.setEnabled(false);
                                }
                            } else {
                                save.setEnabled(false);
                            }

                        } else {

                            save.setEnabled(false);
                        }

                    } else {
                        save.setEnabled(false);
                    }

                } else {
                    save.setEnabled(false);
                }

            } else {

                ephone_number.setError("Please enter the valid phone number");
                save.setEnabled(false);
            }
        } else {
            person_name.setError("Please enter the valid name");
            save.setEnabled(false);
        }
    }

    private void dataLoad() {
        SharedPreferences sharedPrefere = getSharedPreferences("address", MODE_PRIVATE);
        String namek = sharedPrefere.getString("key_name", "");
        String phonek = sharedPrefere.getString("key_phone", "");
        String flatk = sharedPrefere.getString("key_flat", "");

        String localityk = sharedPrefere.getString("key_locality", "");
        String cityk = sharedPrefere.getString("key_city", "");
        String statek = sharedPrefere.getString("key_state", "");

        String pinnok = sharedPrefere.getString("key_pin", "");

        AddressManager.person_namess = sharedPrefere.getString("key_name", "");
        AddressManager.phone_noss = sharedPrefere.getString("key_phone", "");
        AddressManager.flatss = sharedPrefere.getString("key_flat", "");

        AddressManager.localityss = sharedPrefere.getString("key_locality", "");
        AddressManager.cityss = sharedPrefere.getString("key_city", "");
        AddressManager.states = sharedPrefere.getString("key_state", "");

        AddressManager.pinno = sharedPrefere.getString("key_pin", "");

        Sname.setText("Full Name : " + namek);
        Sphone.setText("Phone No : " + phonek);
        Sflat.setText("Flat : " + flatk);
        Slocality.setText("Locality : " + localityk);
        Scity.setText("City : " + cityk);
        Sstate.setText("State : " + statek);
        Spinno.setText("Pin No : " + pinnok);

    }



   /* private void checkAddressAvailablr() {
        if (TextUtils.isEmpty(AddressManager.person_namess))
        {
            if (TextUtils.isEmpty(AddressManager.person_namess))
            {
                    if (TextUtils.isEmpty(AddressManager.person_namess))
                    {
                        if (TextUtils.isEmpty(AddressManager.person_namess))
                        {
                            if (TextUtils.isEmpty(AddressManager.person_namess))
                            {
                                if (TextUtils.isEmpty(AddressManager.person_namess))
                                {
                                    if (TextUtils.isEmpty(AddressManager.person_namess))
                                    {
                                        //  scrollView.setVisibility(View.VISIBLE);
                                      //  adressViewlayout.setVisibility(View.GONE);
                                     //   adsupdate.setVisibility(View.GONE);
                                        address_continue.setEnabled(false);
                                        address_continue.setText("Add Addresss");
                                        address_continue.setVisibility(View.GONE);

                                    }
                                    else
                                    {
                                        adsupdate.setVisibility(View.VISIBLE);
                                        adressViewlayout.setVisibility(View.VISIBLE);
                                        scrollView.setVisibility(View.GONE);
                                        address_continue.setVisibility(View.VISIBLE);
                                    }

                                }
                                else
                                {
                                    adsupdate.setVisibility(View.VISIBLE);
                                    adressViewlayout.setVisibility(View.VISIBLE);
                                    scrollView.setVisibility(View.GONE);
                                    address_continue.setVisibility(View.VISIBLE);
                                }
                            }
                            else
                            {
                                adsupdate.setVisibility(View.VISIBLE);
                                adressViewlayout.setVisibility(View.VISIBLE);
                                scrollView.setVisibility(View.GONE);
                                address_continue.setVisibility(View.VISIBLE);
                            }
                        }
                        else
                        {
                            adsupdate.setVisibility(View.VISIBLE);
                            adressViewlayout.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                            address_continue.setVisibility(View.VISIBLE);
                        }
                    }
                    else
                        {
                            adsupdate.setVisibility(View.VISIBLE);
                            adressViewlayout.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                            address_continue.setVisibility(View.VISIBLE);
                        }



            }
            else {
                adsupdate.setVisibility(View.VISIBLE);
                adressViewlayout.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                address_continue.setVisibility(View.VISIBLE);
            }
        }
        else {
            adsupdate.setVisibility(View.VISIBLE);
            adressViewlayout.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            address_continue.setVisibility(View.VISIBLE);
        }



    }*/


    private void checkAddressAvailablr() {
        if (AddressManager.person_namess.isEmpty()) {
            if (AddressManager.person_namess.isEmpty()) {
                if (AddressManager.person_namess.isEmpty()) {
                    if (AddressManager.person_namess.isEmpty()) {
                        if (AddressManager.person_namess.isEmpty()) {
                            if (AddressManager.person_namess.isEmpty()) {
                                if (AddressManager.person_namess.isEmpty()) {
                                    scrollView.setVisibility(View.VISIBLE);
                                    adressViewlayout.setVisibility(View.GONE);
                                    adsupdate.setVisibility(View.GONE);
                                    address_continue.setEnabled(false);
                                    address_continue.setText("Add Addresss");
                                    address_continue.setVisibility(View.GONE);

                                } else {
                                    adsupdate.setVisibility(View.VISIBLE);
                                    adressViewlayout.setVisibility(View.VISIBLE);
                                    scrollView.setVisibility(View.GONE);
                                    address_continue.setVisibility(View.VISIBLE);
                                }

                            } else {
                                adsupdate.setVisibility(View.VISIBLE);
                                adressViewlayout.setVisibility(View.VISIBLE);
                                scrollView.setVisibility(View.GONE);
                                address_continue.setVisibility(View.VISIBLE);
                            }
                        } else {
                            adsupdate.setVisibility(View.VISIBLE);
                            adressViewlayout.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                            address_continue.setVisibility(View.VISIBLE);
                        }
                    } else {
                        adsupdate.setVisibility(View.VISIBLE);
                        adressViewlayout.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                        address_continue.setVisibility(View.VISIBLE);
                    }
                } else {
                    adsupdate.setVisibility(View.VISIBLE);
                    adressViewlayout.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                    address_continue.setVisibility(View.VISIBLE);
                }


            } else {
                adsupdate.setVisibility(View.VISIBLE);
                adressViewlayout.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                address_continue.setVisibility(View.VISIBLE);
            }
        } else {
            adsupdate.setVisibility(View.VISIBLE);
            adressViewlayout.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            address_continue.setVisibility(View.VISIBLE);
        }


    }

    private void dataStor() {


        String person_names, phone_nos, citys, states, localitys, flats, pin;

        person_names = person_name.getText().toString();
        phone_nos = ephone_number.getText().toString();
        citys = city.getText().toString();

        states = state.getText().toString();
        localitys = locality.getText().toString();
        flats = flat.getText().toString();

        pin = pinno.getText().toString();


        SharedPreferences save = getSharedPreferences("address", MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putString("key_name", person_names);
        editor.putString("key_phone", phone_nos);
        editor.putString("key_city", citys);

        editor.putString("key_state", states);
        editor.putString("key_locality", localitys);
        editor.putString("key_flat", flats);

        editor.putString("key_pin", pin);
        editor.apply();

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