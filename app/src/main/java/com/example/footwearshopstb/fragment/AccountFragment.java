package com.example.footwearshopstb.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.FAccountAdapter;
import com.example.footwearshopstb.model.FAccountModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }


    RecyclerView recyclerView;
    TextView fullname, email;
    FirebaseFirestore firebaseFirestore;
    ConstraintLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fullname = view.findViewById(R.id.fullname_txt);
        email = view.findViewById(R.id.email_textv);
        constraintLayout = view.findViewById(R.id.profil_layout);

        recyclerView = view.findViewById(R.id.f_a_recycleview);
        List<FAccountModel> fAccountModelList = new ArrayList<>();
        fAccountModelList.add(new FAccountModel("My Order"));
        fAccountModelList.add(new FAccountModel("Address Manager"));

        SharedPreferences sh = view.getContext().getSharedPreferences("login", MODE_PRIVATE);
        Boolean check = sh.getBoolean("flag", false);

        if (check) {
            fAccountModelList.add(new FAccountModel("Sign Out"));
            firebaseFirestore.collection("USER")
                    .document(AddressManager.user_Id)
                    .collection("profile")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    fullname.setText(documentSnapshot.get("full_name").toString());
                                    email.setText(documentSnapshot.get("email").toString());
                                }
                                constraintLayout.setVisibility(View.VISIBLE);
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(view.getContext(), error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            fAccountModelList.add(new FAccountModel("Sign In"));

        }
        /* fAccountModelList.add(new FAccountModel("Logout"));*/

        FAccountAdapter fAccountAdapter = new FAccountAdapter(fAccountModelList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fAccountAdapter);


        return view;
    }

}