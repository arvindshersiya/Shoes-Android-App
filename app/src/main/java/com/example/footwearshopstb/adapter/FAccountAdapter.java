package com.example.footwearshopstb.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footwearshopstb.activity.AddressAddActivity;
import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.activity.LoginActivity;
import com.example.footwearshopstb.activity.MyOrderActivity;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.activity.SignUpActivity;
import com.example.footwearshopstb.model.FAccountModel;

import java.util.List;

public class FAccountAdapter extends RecyclerView.Adapter<FAccountAdapter.ListNameViewHolder> {

    List<FAccountModel> fAccountModelList;

    public FAccountAdapter(List<FAccountModel> fAccountModelList) {
        this.fAccountModelList = fAccountModelList;
    }

    @NonNull
    @Override
    public ListNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_a_recycleview_list_, parent, false);

        return new ListNameViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListNameViewHolder holder, int position) {

        holder.listnam.setText(fAccountModelList.get(position).getListname());
    }

    @Override
    public int getItemCount() {
        return fAccountModelList.size();
    }

    public class ListNameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View view;
        private final TextView listnam;

        public ListNameViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.f_a_recycleview_parent);
            listnam = itemView.findViewById(R.id.f_a_recycleview_list_manu);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position == 0) {

                SharedPreferences sh = itemView.getContext().getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);

                if (check) {

                    Intent gotoorderdetail = new Intent(itemView.getContext(), MyOrderActivity.class);
                    //    Intent gotoorderdetail = new Intent(itemView.getContext(), MyOrderDetailsActivity.class);
                    itemView.getContext().startActivity(gotoorderdetail);

                } else {

                    Dialog singindialog = new Dialog(itemView.getContext());
                    singindialog.setContentView(R.layout.sign_dialog);
                    singindialog.setCancelable(true);

                    singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                    Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                    dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });
                    dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(), SignUpActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });

                    singindialog.show();


                }


                /*Intent gotoorderdetail = new Intent(itemView.getContext(), MyOrderActivity.class);
            //    Intent gotoorderdetail = new Intent(itemView.getContext(), MyOrderDetailsActivity.class);
                itemView.getContext().startActivity(gotoorderdetail);*/
            } else if (position == 1) {

                SharedPreferences sh = itemView.getContext().getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);

                if (check) {
                    AddressManager.chekAcitycity = 1;
                    Intent gotoaddress = new Intent(itemView.getContext(), AddressAddActivity.class);
                    itemView.getContext().startActivity(gotoaddress);

                } else {

                    Dialog singindialog = new Dialog(itemView.getContext());
                    singindialog.setContentView(R.layout.sign_dialog);
                    singindialog.setCancelable(true);

                    singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                    Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                    dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });
                    dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(), SignUpActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });

                    singindialog.show();


                }



               /* AddressManager.chekAcitycity= 1;
                Intent gotoaddress = new Intent(itemView.getContext(), AddressAddActivity.class);
                itemView.getContext().startActivity(gotoaddress);*/
            } else if (position == 2) {
//                Toast.makeText(v.getContext(), "click", Toast.LENGTH_SHORT).show();


                SharedPreferences sh = itemView.getContext().getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);

                if (check) {
                    SharedPreferences shs = itemView.getContext().getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shs.edit();
                    editor.putBoolean("flag", false);
                    editor.apply();
                    dataStor();
                    Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                    itemView.getContext().startActivity(intent);
                    ((Activity) itemView.getContext()).finish();


                } else {

                    //fAccountModelList.get(position).setListname("Login");
                    Intent intent = new Intent(itemView.getContext(), LoginActivity.class);
                    itemView.getContext().startActivity(intent);
                    ((Activity) itemView.getContext()).finish();
                   /* Dialog singindialog  = new Dialog(itemView.getContext());
                    singindialog.setContentView(R.layout.sign_dialog);
                    singindialog.setCancelable(true);

                    singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                    Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                    dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(),LoginActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });
                    dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(itemView.getContext(),SignUpActivity.class);
                            itemView.getContext().startActivity(intent);

                        }
                    });

                    singindialog.show();
*/

                }







              /*  SharedPreferences sh = itemView.getContext().getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();

                editor.putBoolean("flag", false);
                editor.apply();
                Intent  intent = new Intent(itemView.getContext(), LoginActivity.class);
                itemView.getContext().startActivity(intent);
                ((Activity)itemView.getContext()).finish();*/

            }


        }

        //clear address data
        private void dataStor() {

            SharedPreferences save = itemView.getContext().getSharedPreferences("address", MODE_PRIVATE);
            SharedPreferences.Editor editor = save.edit();
            editor.putString("key_name", "");
            editor.putString("key_phone", "");
            editor.putString("key_city", "");

            editor.putString("key_state", "");
            editor.putString("key_locality", "");
            editor.putString("key_flat", "");

            editor.putString("key_pin", "");
            editor.apply();

        }
    }
}




