package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.MyOrderAdapter;
import com.example.footwearshopstb.model.MyOrderModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyOrderActivity extends AppCompatActivity {

    private RecyclerView myOrderView;
    Toolbar toolbar;

    TextView notProduct;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);


        toolbar = findViewById(R.id.myorder_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("My Order");
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        notProduct = findViewById(R.id.notfound_my_order);

        firebaseFirestore = FirebaseFirestore.getInstance();

        myOrderView = findViewById(R.id.f_myorder_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderView.setLayoutManager(layoutManager);

        List<MyOrderModel> myOrderModelList = new ArrayList<>();
/*

        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Delivered on Sun, 16th Jan 2022"));
        myOrderModelList.add(new MyOrderModel(R.drawable.vnts, "vent","Cancelled"));
*/
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderModelList);
        myOrderView.setAdapter(myOrderAdapter);
        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("Order")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                             /*   CartItemModel cartItemModel = documentSnapshot.toObject(CartItemModel.class);
                               cartItemModelList.add(cartItemModel);
                                cartAdapter.notifyDataSetChanged();
*/
                                myOrderModelList.add(new MyOrderModel(
                                        documentSnapshot.get("shoeImage").toString(),
                                        documentSnapshot.get("shoeName").toString(),
                                        documentSnapshot.get("shoeQty").toString(),
                                        documentSnapshot.get("shoePrice").toString(),

                                        documentSnapshot.get("shoeId").toString(),
                                        documentSnapshot.get("totalAmount").toString(),
                                        documentSnapshot.get("orderID").toString(),
                                        documentSnapshot.get("paymentMethod").toString(),

                                        documentSnapshot.get("paymentStatus").toString(),
                                        documentSnapshot.get("address").toString(),
                                        documentSnapshot.get("orderDateTime").toString(),
                                        documentSnapshot.get("deliverStatus").toString(),
                                        documentSnapshot.get("shoeSize").toString(),
                                        documentSnapshot.get("fullName").toString(),
                                        documentSnapshot.get("pinNo").toString(),

                                        documentSnapshot.get("packedDateTime").toString(),
                                        documentSnapshot.get("shippedDateTime").toString(),
                                        documentSnapshot.get("deliveredDateTime").toString(),
                                        documentSnapshot.get("cancelledDateTime").toString(),
                                        documentSnapshot.get("paymentStatus").toString()

                                ));
                            }
                            if (myOrderModelList.size() == 0) {
                                notProduct.setVisibility(View.VISIBLE);
                                myOrderView.setVisibility(View.GONE);
                            }


                            myOrderAdapter.notifyDataSetChanged();


                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


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
