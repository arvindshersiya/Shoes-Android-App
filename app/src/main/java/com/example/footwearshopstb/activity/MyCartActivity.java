package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.CartAdapter;
import com.example.footwearshopstb.model.CartItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCartActivity extends AppCompatActivity {

    RecyclerView cartItemRecyclerview;
    Toolbar toolbar;
    Button continuebtn;
    FirebaseFirestore firebaseFirestore;
    List<CartItemModel> cartItemModelList;


    LinearLayout linearLayout;
    private TextView totalAmt, textTotalamt;
    TextView not_item;

    public static CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        toolbar = findViewById(R.id.c_i_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Cart");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        continuebtn = findViewById(R.id.cart_confirm_btn);
        firebaseFirestore = FirebaseFirestore.getInstance();

        // totalamt = findViewById(R.id.f_c_total_cart_amout);

        linearLayout = findViewById(R.id.linearLayout);
        not_item = findViewById(R.id.text_no_item);


        cartItemRecyclerview = findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemRecyclerview.setLayoutManager(layoutManager);

        cartItemModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItemModelList);
        cartItemRecyclerview.setAdapter(cartAdapter);
        //cartItemModelList.add(new CartItemModel(0, "data:image/jpeg;base64,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","Vens","Rs.599/-",1));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.vnts,"Vens","Rs.599/-",1));
//        cartItemModelList.add(new CartItemModel(0, R.drawable.vnts,"Vens","Rs.599/-",1));
//        cartItemModelList.add(new CartItemModel(1, "Price (3 item )","Rs.1555/-","Rs.1555/-"));
//
        textTotalamt = findViewById(R.id.total_amt_text);

        totalAmt = findViewById(R.id.f_c_total_cart_amout);
        IntentFilter intentFilter = new IntentFilter("AllItemTotalAmount");
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver, intentFilter);


        SharedPreferences get = getSharedPreferences("user", MODE_PRIVATE);
        String userid = get.getString("username", "");


        firebaseFirestore.collection("USER")
                .document(userid)
                .collection("AddedToCart")
                .get()
                /*.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

*/
                /*    for (QueryDocumentSnapshot queryDocumentSnapshot: queryDocumentSnapshots){
                        cartItemModel = queryDocumentSnapshot.toObject(CartItemModel.class);
                        cartItemModelList.add(cartItemModel);
                    }
                    cartAdapter.notifyDataSetChanged();

            });*/
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                             /*   CartItemModel cartItemModel = documentSnapshot.toObject(CartItemModel.class);
                               cartItemModelList.add(cartItemModel);
                                cartAdapter.notifyDataSetChanged();
*/
                                cartItemModelList.add(new CartItemModel(
                                        documentSnapshot.get("shoeId").toString(),
                                        documentSnapshot.get("shoeImage").toString(),
                                        documentSnapshot.get("shoeName").toString(),
                                        documentSnapshot.get("shoePrice").toString(),
                                        documentSnapshot.get("shoeSize").toString(),
                                        documentSnapshot.get("shoeQty").toString(),
                                        documentSnapshot.get("totalAmount").toString(),
                                        documentSnapshot.get("orderID").toString()));

                            }
                            cartAdapter.notifyDataSetChanged();

                            if (cartItemModelList.size() != 0) {
                                linearLayout.setVisibility(View.VISIBLE);
                                cartItemRecyclerview.setVisibility(View.VISIBLE);
                            } else if (cartItemModelList.size() == 0) {
                                not_item.setVisibility(View.VISIBLE);
                            }

                   /*         for (int x=0; x < arrayList.size() ; x++){
                                String amt = arrayList.get(x);
                                long aa = Long.parseLong(amt);
                                totalamaout = totalamaout+aa;

                            }


*/


/*
                            arrayList.add(cartItemModelList.);
                            arrayList = new ArrayList<>(cartItemModelList.size());
                            String t;
                            t = (String) arrayList.get(1);
                            totalamt.setText(""+t);*/


                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                /*if (cartItemModelList.size() > 0){
                    continuebtn.setEnabled(true);
                }*/


        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddressManager.check_Cart_Buy_btn = 1;
                AddressManager.chekAcitycity = 0;
                Intent intent = new Intent(MyCartActivity.this, AddressAddActivity.class);
                startActivity(intent);

            }
        });


    }


    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int allAmt = intent.getIntExtra("totalAmt", 0);
            totalAmt.setText("₹" + allAmt + "/-");

        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}