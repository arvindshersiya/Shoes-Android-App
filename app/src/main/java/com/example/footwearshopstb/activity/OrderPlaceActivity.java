package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.PlaceOrderAdapter;
import com.example.footwearshopstb.model.PlaceOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class OrderPlaceActivity extends AppCompatActivity implements PaymentResultListener {

    Toolbar toolbar;

    FirebaseFirestore firebaseFirestore;

    RecyclerView itemrecyclerView;

    Button plceeOrder;

    ConstraintLayout orderPlaceLayout;

    PlaceOrderAdapter placeOrderAdapter;
    List<PlaceOrderModel> placeOrderModelList;

    String address, fullname, pin;

    int allAmt;

    TextView Sname;
    TextView Sphone;
    TextView Sflat;
    TextView Slocality;
    TextView Scity;
    TextView Sstate;
    TextView Spinno;


    //Order Confirmed widget

    // TextView  orderId;
    Button continueBtb;
    TextView order_id;
    ConstraintLayout orderConfirmedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_place);


        toolbar = findViewById(R.id.p_o_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Place to Order");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();


        Sname = findViewById(R.id.p_o_setname);
        Sphone = findViewById(R.id.p_o_setphone);
        Sflat = findViewById(R.id.p_o_setflat);
        Slocality = findViewById(R.id.p_o_setlocality);
        Scity = findViewById(R.id.p_o_setncity);
        Sstate = findViewById(R.id.p_o_setstate);
        Spinno = findViewById(R.id.p_o_setpin);

        plceeOrder = findViewById(R.id.p_o_placeOrder_btn);
        // order Place
        orderPlaceLayout = findViewById(R.id.order_place_layout);

        // order confirmed widget


        order_id = findViewById(R.id.order_id);
        orderConfirmedLayout = findViewById(R.id.order_success_layout);
        //  orderId = findViewById(R.id.order_id);
        continueBtb = findViewById(R.id.continue_btn);


        //
        IntentFilter intentFilter = new IntentFilter("AllItemAmount");
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver, intentFilter);


        continueBtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        itemrecyclerView = findViewById(R.id.p_o_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemrecyclerView.setLayoutManager(layoutManager);

        placeOrderModelList = new ArrayList<>();
        placeOrderAdapter = new PlaceOrderAdapter(placeOrderModelList);
        itemrecyclerView.setAdapter(placeOrderAdapter);

        if (AddressManager.check_Cart_Buy_btn == 1) {

            firebaseFirestore.collection("USER")
                    .document(AddressManager.user_Id)
                    .collection("AddedToCart")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    placeOrderModelList.add(new PlaceOrderModel(
                                            documentSnapshot.get("shoeId").toString(),
                                            documentSnapshot.get("shoeImage").toString(),
                                            documentSnapshot.get("shoeName").toString(),
                                            documentSnapshot.get("shoePrice").toString(),

                                            documentSnapshot.get("shoeSize").toString(),
                                            documentSnapshot.get("shoeQty").toString(),
                                            documentSnapshot.get("totalAmount").toString(),
                                            documentSnapshot.get("orderID").toString()));

                                }
                                placeOrderAdapter.notifyDataSetChanged();


                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else if (AddressManager.check_Cart_Buy_btn == 2) {

            firebaseFirestore.collection("USER")
                    .document(AddressManager.user_Id)
                    .collection("Buybtn")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    placeOrderModelList.add(new PlaceOrderModel(
                                            documentSnapshot.get("shoeId").toString(),
                                            documentSnapshot.get("shoeImage").toString(),
                                            documentSnapshot.get("shoeName").toString(),
                                            documentSnapshot.get("shoePrice").toString()
                                            ,
                                            documentSnapshot.get("shoeSize").toString(),
                                            documentSnapshot.get("shoeQty").toString(),
                                            documentSnapshot.get("totalAmount").toString(),
                                            documentSnapshot.get("orderID").toString()));

                                }
                                placeOrderAdapter.notifyDataSetChanged();


                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }


        plceeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog paymentdialog = new Dialog(OrderPlaceActivity.this);
                paymentdialog.setContentView(R.layout.payment_dialog);
                paymentdialog.setCancelable(true);

                paymentdialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Button dialogCodbtn = paymentdialog.findViewById(R.id.p_m_cod_btn);
                Button dialogPytembtn = paymentdialog.findViewById(R.id.p_m_pytem_btn);

                dialogCodbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        paymentdialog.dismiss();
                        codMethod();

                    }
                });
                dialogPytembtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        paymentdialog.dismiss();
                     /*   Intent intent = new Intent(OrderPlaceActivity.this,SignUpActivity.class);
                        overridePendingTransition(R.anim.from_left,R.anim.fromout_right);
                        startActivity(intent);*/

                        makePaymentOnline(allAmt);
                    }
                });

                paymentdialog.show();


                //  codMethod();
            }
        });

        dataLoad();

    }

    private void makePaymentOnline(int allAmt) {

        String amt = String.valueOf(allAmt);

        final Activity activity = this;

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_CfCZTkBmpIfmm0");
        //    checkout.setImage(R.drawable.loo);


        double finalAmount = Float.parseFloat(amt) * 100;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Footwear Shop");
            options.put("description", "payment for your order");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", finalAmount + "");//300 X 100
            options.put("prefill.email", AddressManager.emailx + "");
            options.put("prefill.contact", AddressManager.phone_noss + "");

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }


    }

    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            allAmt = intent.getIntExtra("totalAmtplcee", 0);

            // totalAmt.setText("₹"+allAmt+"/-");

        }
    };

    private void codMethod() {

        String savecurrentdate, savecurrenttime;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        savecurrentdate = dateFormat.format(calendar.getTime());
        savecurrenttime = timeFormat.format(calendar.getTime());

        String paymentMethod = "Cod";
        String paymentStatus = "unpaid";
        for (PlaceOrderModel placeOrderModel : placeOrderModelList) {


            final HashMap<String, Object> cartmap = new HashMap<>();


            cartmap.put("shoeImage", placeOrderModel.getProductimage());
            cartmap.put("shoeName", placeOrderModel.getProducttitle());
            cartmap.put("shoePrice", placeOrderModel.getProductprice());
            cartmap.put("shoeSize", placeOrderModel.getProductsize());

            cartmap.put("shoeId", placeOrderModel.getProductid());
            cartmap.put("shoeQty", placeOrderModel.getProductquantity());
            cartmap.put("totalAmount", placeOrderModel.getTotalAmount());
            cartmap.put("orderID", placeOrderModel.getGetID());

            cartmap.put("paymentMethod", paymentMethod);
            cartmap.put("paymentStatus", paymentStatus);
            cartmap.put("address", address.trim().toLowerCase());
            cartmap.put("fullName", fullname.toLowerCase());
            cartmap.put("pinNo", pin);

            cartmap.put("orderDateTime", savecurrentdate);
            cartmap.put("deliverStatus", "Ordered");

            cartmap.put("packedDateTime", savecurrentdate);
            cartmap.put("shippedDateTime", savecurrentdate);
            cartmap.put("deliveredDateTime", savecurrentdate);
            cartmap.put("cancelledDateTime", savecurrentdate);

            //     cartmap.put("packedDateTime  ", "Order Arriving");


            firebaseFirestore.collection("USER")
                    .document(AddressManager.user_Id)
                    .collection("Order")
                    .add(cartmap)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(getApplicationContext(), "Product add in cart", Toast.LENGTH_SHORT).show();
                                cartClearItem();
                                buyDataClear();
                                order_id.setText(String.valueOf("Order ID : " + placeOrderModel.getGetID()));
                                orderPlaceLayout.setVisibility(View.GONE);
                                orderConfirmedLayout.setVisibility(View.VISIBLE);
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(OrderPlaceActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }
    ////

    private void onlineMethod() {

        String savecurrentdate, savecurrenttime;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        savecurrentdate = dateFormat.format(calendar.getTime());
        savecurrenttime = timeFormat.format(calendar.getTime());

        String paymentMethod = "razorpay";
        String paymentStatus = "paid";
        for (PlaceOrderModel placeOrderModel : placeOrderModelList) {


            final HashMap<String, Object> cartmap = new HashMap<>();


            cartmap.put("shoeImage", placeOrderModel.getProductimage());
            cartmap.put("shoeName", placeOrderModel.getProducttitle());
            cartmap.put("shoePrice", placeOrderModel.getProductprice());
            cartmap.put("shoeSize", placeOrderModel.getProductsize());

            cartmap.put("shoeId", placeOrderModel.getProductid());
            cartmap.put("shoeQty", placeOrderModel.getProductquantity());
            cartmap.put("totalAmount", placeOrderModel.getTotalAmount());
            cartmap.put("orderID", placeOrderModel.getGetID());

            cartmap.put("paymentMethod", paymentMethod);
            cartmap.put("paymentStatus", paymentStatus);
            cartmap.put("address", address);
            cartmap.put("fullName", fullname);
            cartmap.put("pinNo", pin);

            cartmap.put("orderDateTime", savecurrentdate);
            cartmap.put("deliverStatus", "Ordered");

            cartmap.put("packedDateTime", savecurrentdate);
            cartmap.put("shippedDateTime", savecurrentdate);
            cartmap.put("deliveredDateTime", savecurrentdate);
            cartmap.put("cancelledDateTime", savecurrentdate);

            //     cartmap.put("packedDateTime  ", "Order Arriving");


            firebaseFirestore.collection("USER")
                    .document(AddressManager.user_Id)
                    .collection("Order")
                    .add(cartmap)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(getApplicationContext(), "Product add in cart", Toast.LENGTH_SHORT).show();
                                cartClearItem();
                                buyDataClear();
                                order_id.setText(String.valueOf("Order ID : " + placeOrderModel.getGetID()));
                               /* orderPlaceLayout.setVisibility(View.GONE);
                                orderConfirmedLayout.setVisibility(View.VISIBLE);*/
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(OrderPlaceActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }


    //
    private void cartClearItem() {

        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("AddedToCart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                String d_id = documentSnapshot.getId();
                                firebaseFirestore.collection("USER")
                                        .document(AddressManager.user_Id)
                                        .collection("AddedToCart")
                                        .document(d_id)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "Not remove", Toast.LENGTH_SHORT).show();


                                            }
                                        });
                            }
                            /*Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();*/
                            //DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void orderPaymentMethod() {

    }


    @Override
    public void onBackPressed() {
        buyDataClear();
        //super.onBackPressed();
        super.onBackPressed();
        finish();
    }

    private void buyDataClear() {
        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("Buybtn")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                String d_id = documentSnapshot.getId();
                                firebaseFirestore.collection("USER")
                                        .document(AddressManager.user_Id)
                                        .collection("Buybtn")
                                        .document(d_id)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {


                                            }
                                        });
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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


        Sname.setText("Full Name : " + namek);
        Sphone.setText("Phone No : " + phonek);
        Sflat.setText("Flat : " + flatk);
        Slocality.setText("Locality : " + localityk);
        Scity.setText("City : " + cityk);
        Sstate.setText("State : " + statek);
        Spinno.setText("Pin No : " + pinnok);

        pin = pinnok;
        fullname = namek;
        address = phonek + ", " + flatk + ", " + localityk + ", " + cityk + ", " + statek;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            buyDataClear();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPaymentSuccess(String s) {
        onlineMethod();
        // order_id.setText(String.valueOf("Order ID : "+placeOrderModel.getGetID()));
        orderPlaceLayout.setVisibility(View.GONE);
        orderConfirmedLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}