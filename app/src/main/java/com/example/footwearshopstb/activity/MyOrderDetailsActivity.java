package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.MyOrderAdapter;
import com.example.footwearshopstb.model.MyOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyOrderDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    MyOrderModel myOrderModel = null;
    ImageView productImage, orderIndicator, packedIndicator, shippedIndicator, deliverdIndicator;
    TextView productTitle, prodcutQty, productprice, fullName, pinNo, address, totalamt, shoessize, payment_status;
    String getAddress;
    Button cancelOrder, returnOrder;
    FirebaseFirestore firebaseFirestore;
    String orderId;
    static MyOrderAdapter myOrderAdapter;
    String orderStatus;


    private ProgressBar o_p_progress, o_s_progrees, o_d_progress;
    private TextView orderDate, packedDate, shippedDate, delivereDate;
    private TextView orderBody, packedBody, shippedBody, delivereBody;
    private TextView orderTitle, packedTitle, shippedTitle, delivereTitle;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        toolbar = findViewById(R.id.myorder_details_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Order Details");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImage = findViewById(R.id.o_d_l_producImage);
        productTitle = findViewById(R.id.o_d_l_producTitle);
        prodcutQty = findViewById(R.id.o_d_l_producqty);
        productprice = findViewById(R.id.o_d_l_producPrice);
        shoessize = findViewById(R.id.o_d_l_size);

        fullName = findViewById(R.id.s_d_person_name);
        pinNo = findViewById(R.id.s_d_adress_pin);
        address = findViewById(R.id.s_d_person_adress);
        totalamt = findViewById(R.id.gettotalamtz_txt);
        cancelOrder = findViewById(R.id.orderCancel);
        returnOrder = findViewById(R.id.orderReturn);
        firebaseFirestore = FirebaseFirestore.getInstance();

        payment_status = findViewById(R.id.s_d_payment_status);

        o_p_progress = findViewById(R.id.progressBar);
        o_s_progrees = findViewById(R.id.progressBartwo);
        o_d_progress = findViewById(R.id.progressBarthree);

        orderDate = findViewById(R.id.o_d_l_p_ordered_date_status);
        packedDate = findViewById(R.id.o_d_l_p_packed_date_status);
        shippedDate = findViewById(R.id.o_d_l_p_shipped_date_status);
        delivereDate = findViewById(R.id.o_d_l_p_delivered_date_status);

        orderBody = findViewById(R.id.o_d_l_p_order_title_description);
        shippedBody = findViewById(R.id.o_d_l_p_shipped_title_description);
        packedBody = findViewById(R.id.o_d_l_p_packed_title_description);
        delivereBody = findViewById(R.id.o_d_l_p_delivered_title_description);

        orderIndicator = findViewById(R.id.o_d_l_dot_indicator);
        packedIndicator = findViewById(R.id.o_d_l_dot_indicatortwo);
        shippedIndicator = findViewById(R.id.o_d_l_dot_indicatorthree);
        deliverdIndicator = findViewById(R.id.o_d_l_dot_indicatorfour);

        orderTitle = findViewById(R.id.o_d_l_p_order_title_status);
        shippedTitle = findViewById(R.id.o_d_l_p_shipped_title_status);
        packedTitle = findViewById(R.id.o_d_l_p_packed_title_status);
        delivereTitle = findViewById(R.id.o_d_l_p_delivered_title_statusa);

        final Object object = getIntent().getSerializableExtra("idd");

        if (object instanceof MyOrderModel) {

            myOrderModel = (MyOrderModel) object;
        }

        String img = myOrderModel.getProductImage();
        //   String id = horizontalProductScrollModel.getProduct_ID();

        if (myOrderModel != null) {
            Glide.with(getApplicationContext()).load(img).into(productImage);
            productTitle.setText(myOrderModel.getProductTitle());
            prodcutQty.setText("Qty: " + myOrderModel.getProductqty());
            productprice.setText("Rs. " + myOrderModel.getPrductPrice() + "/-");
            shoessize.setText("Size: " + myOrderModel.getShoeSize());
            fullName.setText("Name : " + myOrderModel.getFullName());
            pinNo.setText("Pin no : " + myOrderModel.getPinNo());
            address.setText("Address : " + myOrderModel.getAddress());
            orderId = myOrderModel.getOrderID();
            totalamt.setText(myOrderModel.getPrducttotalAmount());
            orderStatus = myOrderModel.getDeliverStatus();

            orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));
            packedDate.setText(String.valueOf(myOrderModel.getPackedDateTime()));
            shippedDate.setText(String.valueOf(myOrderModel.getShippedDateTime()));
            delivereDate.setText(String.valueOf(myOrderModel.getDeliveredDateTime()));
            payment_status.setText(String.valueOf("Payment Status : " + myOrderModel.getPayment_status()));
            //deliveryDateFromDB =String.valueOf(delivereDate);


        }


        returnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderReturn();
            }
        });


        switch (orderStatus) {

            case "Ordered":
                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));

                packedIndicator.setVisibility(View.GONE);
                packedBody.setVisibility(View.GONE);
                packedDate.setVisibility(View.GONE);
                packedTitle.setVisibility(View.GONE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                deliverdIndicator.setVisibility(View.GONE);
                delivereBody.setVisibility(View.GONE);
                delivereDate.setVisibility(View.GONE);
                delivereTitle.setVisibility(View.GONE);

                o_d_progress.setVisibility(View.GONE);
                o_p_progress.setVisibility(View.GONE);
                o_s_progrees.setVisibility(View.GONE);
                break;

            case "Packed":

                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));


                packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                packedDate.setText(String.valueOf(myOrderModel.getPackedDateTime()));

                packedIndicator.setVisibility(View.VISIBLE);
                packedBody.setVisibility(View.VISIBLE);
                packedDate.setVisibility(View.VISIBLE);
                packedTitle.setVisibility(View.VISIBLE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                deliverdIndicator.setVisibility(View.GONE);
                delivereBody.setVisibility(View.GONE);
                delivereDate.setVisibility(View.GONE);
                delivereTitle.setVisibility(View.GONE);

                o_d_progress.setVisibility(View.GONE);
                o_p_progress.setVisibility(View.VISIBLE);
                o_p_progress.setProgress(100);
                o_p_progress.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                o_s_progrees.setVisibility(View.GONE);

                break;

            case "Shipped":

                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));

                packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                packedDate.setText(String.valueOf(myOrderModel.getPackedDateTime()));

                shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                shippedDate.setText(String.valueOf(myOrderModel.getShippedDateTime()));

                deliverdIndicator.setVisibility(View.GONE);
                delivereBody.setVisibility(View.GONE);
                delivereDate.setVisibility(View.GONE);
                delivereTitle.setVisibility(View.GONE);

                o_d_progress.setVisibility(View.GONE);
                o_p_progress.setVisibility(View.VISIBLE);
                o_p_progress.setProgress(100);
                o_p_progress.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                o_s_progrees.setVisibility(View.VISIBLE);
                o_s_progrees.setProgress(100);
                o_s_progrees.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                break;

            case "Delivered":

                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));

                packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                packedDate.setText(String.valueOf(myOrderModel.getPackedDateTime()));

                shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                shippedDate.setText(String.valueOf(myOrderModel.getShippedDateTime()));

                deliverdIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                delivereDate.setText(String.valueOf(myOrderModel.getDeliveredDateTime()));


                deliverdIndicator.setVisibility(View.VISIBLE);
                delivereBody.setVisibility(View.VISIBLE);
                delivereDate.setVisibility(View.VISIBLE);
                delivereTitle.setVisibility(View.VISIBLE);
                o_d_progress.setVisibility(View.VISIBLE);
                o_d_progress.setProgress(100);
                o_p_progress.setVisibility(View.VISIBLE);
                o_p_progress.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                o_p_progress.setProgress(100);
                o_s_progrees.setVisibility(View.VISIBLE);
                o_s_progrees.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                o_s_progrees.setProgress(100);

                try {
                    chckReturnTimeLimit();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;

            case "Cancelled":

                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                //   orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));
                deliverdIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                o_p_progress.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                //
                //  orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));
                //   deliverdIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                //    o_p_progress.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                delivereDate.setText(String.valueOf(myOrderModel.getCancelledDateTime()));

                cancelOrder.setVisibility(View.GONE);
                returnOrder.setVisibility(View.GONE);

                deliverdIndicator.setVisibility(View.VISIBLE);
                delivereBody.setVisibility(View.VISIBLE);
                delivereBody.setText("Your order has been cancelled.");
                delivereDate.setVisibility(View.VISIBLE);
                delivereTitle.setVisibility(View.VISIBLE);
                delivereTitle.setText("Cancelled");

                packedIndicator.setVisibility(View.GONE);
                packedBody.setVisibility(View.GONE);
                packedDate.setVisibility(View.GONE);
                packedTitle.setVisibility(View.GONE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                o_d_progress.setVisibility(View.GONE);
                o_p_progress.setVisibility(View.VISIBLE);
                o_p_progress.setProgress(100);
                o_s_progrees.setVisibility(View.GONE);
                break;

            case "Return":
                orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));

                orderTitle.setText("Return");
                packedIndicator.setVisibility(View.GONE);
                packedBody.setVisibility(View.GONE);
                packedDate.setVisibility(View.GONE);
                packedTitle.setVisibility(View.GONE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                deliverdIndicator.setVisibility(View.GONE);
                delivereBody.setVisibility(View.GONE);
                delivereDate.setVisibility(View.GONE);
                delivereTitle.setVisibility(View.GONE);

                o_d_progress.setVisibility(View.GONE);
                o_p_progress.setVisibility(View.GONE);
                o_s_progrees.setVisibility(View.GONE);
                break;

        }

        if (orderStatus.matches("Cancelled")) {
            cancelOrder.setVisibility(View.GONE);
        }
        if (orderStatus.matches("Delivered")) {
            cancelOrder.setVisibility(View.GONE);
        }
        if (orderStatus.matches("Return")) {
            cancelOrder.setVisibility(View.GONE);
        }

        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCancel();
            }
        });

    }


    private void chckReturnTimeLimit() throws ParseException {
        String deliveryDateFromDB = delivereDate.getText().toString();
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
        Date d = a.parse(deliveryDateFromDB);

        Object ds = new Object();
        ds = d;
        Calendar c = Calendar.getInstance();
        c.setTime((Date) ds);
        c.add(Calendar.DATE, 8);

        Calendar now = Calendar.getInstance();

        if (!c.after(now)) {
            returnOrder.setVisibility(View.GONE);
        } else if (c.after(now)) {
            returnOrder.setVisibility(View.VISIBLE);
        }


       /* Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     //  String currnet = dateFormat.format(calendar.getTime());

        try{
            calendar.setTime(dateFormat.parse(deliveryDateFromDB));
        }catch(ParseException e){
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH, 6);
        Calendar c = Calendar.getInstance();
        dateFormat.format(c.getTime());


        if (calendar.after(c)){

            returnOrder.setVisibility(View.GONE);
        }
        else {
            returnOrder.setVisibility(View.VISIBLE);

        }*/

    }


    private void orderReturn() {

        Calendar ordwer = Calendar.getInstance();
        String orderCancelledDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orderCancelledDate = dateFormat.format(ordwer.getTime());


        Map<String, Object> deliverStatus = new HashMap<>();


        deliverStatus.put("deliverStatus", "Return");
        deliverStatus.put("deliveredDateTime", orderCancelledDate);

        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("Order")
                .whereEqualTo("orderID", orderId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String d_id = documentSnapshot.getId();
                            firebaseFirestore.collection("USER")
                                    .document(AddressManager.user_Id)
                                    .collection("Order")
                                    .document(d_id)
                                    .update(deliverStatus)
                                    //two field update
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            cancelOrder.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Order Return", Toast.LENGTH_SHORT).show();

                                            returnOrder.setVisibility(View.INVISIBLE);
                                            orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                            orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));
                                            orderTitle.setText("Return");
                                            orderDate.setText(orderCancelledDate + "");
                                            orderBody.setText("Your order has been return.");


                                            deliverdIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                            o_p_progress.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));
                                            delivereDate.setText(String.valueOf(myOrderModel.getDeliveredDateTime()));

                                            /*deliverdIndicator.setVisibility(View.VISIBLE);
                                            delivereBody.setVisibility(View.VISIBLE);
                                            delivereBody.setText("Your order has been cancelled.");
                                            delivereDate.setVisibility(View.VISIBLE);
                                            delivereTitle.setVisibility(View.VISIBLE);
                                            delivereTitle.setText("Cancelled");*/

                                           /* packedIndicator.setVisibility(View.GONE);
                                            packedBody.setVisibility(View.GONE);
                                            packedDate.setVisibility(View.GONE);
                                            packedTitle.setVisibility(View.GONE);

                                            shippedIndicator.setVisibility(View.GONE);
                                            shippedBody.setVisibility(View.GONE);
                                            shippedDate.setVisibility(View.GONE);
                                            shippedTitle.setVisibility(View.GONE);


                                            o_d_progress.setVisibility(View.GONE);
                                            o_p_progress.setVisibility(View.VISIBLE);
                                            o_p_progress.setProgress(100);
                                            o_s_progrees.setVisibility(View.GONE);


*/
                                            packedIndicator.setVisibility(View.GONE);
                                            packedBody.setVisibility(View.GONE);
                                            packedDate.setVisibility(View.GONE);
                                            packedTitle.setVisibility(View.GONE);

                                            shippedIndicator.setVisibility(View.GONE);
                                            shippedBody.setVisibility(View.GONE);
                                            shippedDate.setVisibility(View.GONE);
                                            shippedTitle.setVisibility(View.GONE);

                                            deliverdIndicator.setVisibility(View.GONE);
                                            delivereBody.setVisibility(View.GONE);
                                            delivereDate.setVisibility(View.GONE);
                                            delivereTitle.setVisibility(View.GONE);

                                            o_d_progress.setVisibility(View.GONE);
                                            o_p_progress.setVisibility(View.GONE);
                                            o_s_progrees.setVisibility(View.GONE);
                                            //   MyOrderDetailsActivity.myOrderAdapter.notifyDataSetChanged();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Not remove", Toast.LENGTH_SHORT).show();


                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private void orderCancel() {

        Calendar ordwer = Calendar.getInstance();
        String orderCancelledDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        orderCancelledDate = dateFormat.format(ordwer.getTime());


        Map<String, Object> deliverStatus = new HashMap<>();
        deliverStatus.put("deliverStatus", "Cancelled");
        deliverStatus.put("cancelledDateTime", orderCancelledDate);

        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("Order")
                .whereEqualTo("orderID", orderId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String d_id = documentSnapshot.getId();
                            firebaseFirestore.collection("USER")
                                    .document(AddressManager.user_Id)
                                    .collection("Order")
                                    .document(d_id)
                                    .update(deliverStatus)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {

                                            cancelOrder.setVisibility(View.GONE);
                                            Toast.makeText(getApplicationContext(), "Order Cancel", Toast.LENGTH_SHORT).show();

                                            orderIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                                            orderDate.setText(String.valueOf(myOrderModel.getOrderDateTime()));

                                            deliverdIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                                            o_p_progress.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                                            delivereDate.setText("" + orderCancelledDate);

                                            deliverdIndicator.setVisibility(View.VISIBLE);
                                            delivereBody.setVisibility(View.VISIBLE);
                                            delivereBody.setText("Your order has been cancelled.");
                                            delivereDate.setVisibility(View.VISIBLE);
                                            delivereTitle.setVisibility(View.VISIBLE);
                                            delivereTitle.setText("Cancelled");

                                            packedIndicator.setVisibility(View.GONE);
                                            packedBody.setVisibility(View.GONE);
                                            packedDate.setVisibility(View.GONE);
                                            packedTitle.setVisibility(View.GONE);

                                            shippedIndicator.setVisibility(View.GONE);
                                            shippedBody.setVisibility(View.GONE);
                                            shippedDate.setVisibility(View.GONE);
                                            shippedTitle.setVisibility(View.GONE);


                                            o_d_progress.setVisibility(View.GONE);
                                            o_p_progress.setVisibility(View.VISIBLE);
                                            o_p_progress.setProgress(100);
                                            o_s_progrees.setVisibility(View.GONE);

                                            //   MyOrderDetailsActivity.myOrderAdapter.notifyDataSetChanged();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Not remove", Toast.LENGTH_SHORT).show();


                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {
        /// super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MyOrderActivity.class);
        startActivity(intent);
        finish();

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