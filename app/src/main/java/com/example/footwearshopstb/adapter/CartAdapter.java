package com.example.footwearshopstb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.activity.MyCartActivity;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.CartItemModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {


    FirebaseFirestore firebaseFirestore;

    Context context;

    public List<CartItemModel> cartItemModelList;

    long totalAllItemAmount = 0;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return CartItemModel.CART_ITEM_;

        /*switch (cartItemModelList.get(position).getType()){


            case 0:
                return CartItemModel.CART_ITEM_;


            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return  -1;
        }*/

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cartitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartItemViewHolder(cartitem);


       /* switch (viewType){

            case CartItemModel.CART_ITEM_:
                View cartitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
                return new CartItemViewHolder(cartitem);

            case CartItemModel.TOTAL_AMOUNT:
                View carttotalamount = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount,parent,false);
                return new CartTotalAmountViewHolder(carttotalamount);


            default:
                return null;


        }*/
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        String resource = cartItemModelList.get(position).getProductimage();
        String title = cartItemModelList.get(position).getProducttitle();
        String pprice = cartItemModelList.get(position).getProductprice();
        String pqty = cartItemModelList.get(position).getProductquantity();
        String totalamt = cartItemModelList.get(position).getTotalAmount();
        //  String id = cartItemModelList.get(position).getGetID();




/*        List<Integer> integers = new ArrayList<>();
        integers.add((Integer) cartItemModelList.get(position).getTotalAmount());

       *//* for (int i = 0; i < cartItemModelList.size(); i++) {
            integers.add((Integer) cartItemModelList.get(position).getTotalAmount());
        }*//*

        int a = 0;
        int b = 0;
        for (int i = 0; i <integers.size() ; i++) {
            a = integers.get(i);
            b = b+ a;
        }*/



  /*     // totalAllItemAmount = totalAllItemAmount+totalamt ;
        Intent intent = new Intent("AllItemTotalAmount");
        intent.putExtra("totalAmt", b);
        LocalBroadcastManager.getInstance(holder.itemView.getContext()).sendBroadcast(intent);
*/
        /*Intent imntent = new Intent();
        intent.setAction("AllItemTotalAmount");
        intent.putExtra("totalAmt", 55);
        holder.itemView.getContext().sendBroadcast(imntent);
*/

      /*  if ((cartItemModelList.size() != 0)){


            for (CartItemModel i : cartItemModelList.get(position).getTotalAmount())
            {

            }



        }
*/


        ((CartItemViewHolder) holder).setItemDetails(resource, title, pprice, pqty, totalamt);


        int totalaml = 0;
        for (int x = 0; x < cartItemModelList.size(); x++) {

            totalaml = totalaml + Integer.parseInt(cartItemModelList.get(x).getTotalAmount());

        }

        Intent intent = new Intent("AllItemTotalAmount");
        intent.putExtra("totalAmt", totalaml);
        LocalBroadcastManager.getInstance(holder.itemView.getContext()).sendBroadcast(intent);









       /* switch ( cartItemModelList.get(position).getType()){
            case  CartItemModel.CART_ITEM_:
                String resource = cartItemModelList.get(position).getProductimage();
                String title = cartItemModelList.get(position).getProducttitle();
                String pprice = cartItemModelList.get(position).getProductprice();

                ((CartItemViewHolder) holder).setItemDetails(resource,title,pprice);

                break;
            case CartItemModel.TOTAL_AMOUNT:
                String total_item = cartItemModelList.get(position).getTotalitem();
                String total_itemprice = cartItemModelList.get(position).getTotalitemprice();
                String total_amount = cartItemModelList.get(position).getTotalamount();
                ((CartTotalAmountViewHolder) holder).setTotalAmount(total_item,total_itemprice,total_amount);
                break;
            default:
                return;
        }*/

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }


    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView productimage;
        private final TextView producttitle;
        private final TextView productprice;
        private final TextView productquantity;

        private final TextView price_item_count;
        private final TextView price_item_amt;
        private final TextView prduct_rm;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.c_i_product_image);
            producttitle = itemView.findViewById(R.id.c_i_product_title);
            productprice = itemView.findViewById(R.id.c_i_product_price);
            productquantity = itemView.findViewById(R.id.c_i_prduct_qty);

            price_item_count = itemView.findViewById(R.id.c_i_product_price_item_txtv);
            price_item_amt = itemView.findViewById(R.id.c_i_product_price_item_amt);
            prduct_rm = itemView.findViewById(R.id.c_i_prduct_rm);


        }


        String qut;

        private void setItemDetails(String resource, String title, String productprices, String proqty, String totalamt) {
            // productimage.setImageResource(resource);
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.icon_img)).into(productimage);
            producttitle.setText(title);
            productprice.setText("₹" + productprices + "/-");
            productquantity.setText("Qty: " + proqty);
            price_item_count.setText("Price (Qty " + proqty + ")");
            price_item_amt.setText("₹" + totalamt + "/-");
            // totalAmt.setText("Rs. " + totalAllItemAmount + "/-");


            prduct_rm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //prduct_rm.setEnabled(false);
                    String product_id = cartItemModelList.get(getAdapterPosition()).getGetID();
                    firebaseFirestore = FirebaseFirestore.getInstance();


                    firebaseFirestore.collection("USER")
                            .document(AddressManager.user_Id)
                            .collection("AddedToCart")
                            .whereEqualTo("orderID", product_id)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                        String d_id = documentSnapshot.getId();
                                        firebaseFirestore.collection("USER")
                                                .document(AddressManager.user_Id)
                                                .collection("AddedToCart")
                                                .document(d_id)
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {

                                                        cartItemModelList.remove(getAdapterPosition());
                                                        MyCartActivity.cartAdapter.notifyDataSetChanged();


                                                        int totalaml = 0;
                                                        for (int x = 0; x < cartItemModelList.size(); x++) {

                                                            totalaml = totalaml + Integer.parseInt(cartItemModelList.get(x).getTotalAmount());

                                                        }

                                                        Intent intent = new Intent("AllItemTotalAmount");
                                                        intent.putExtra("totalAmt", totalaml);
                                                        LocalBroadcastManager.getInstance(itemView.getContext()).sendBroadcast(intent);


                                                        Toast.makeText(itemView.getContext(), "Item Remove", Toast.LENGTH_SHORT).show();


                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(itemView.getContext(), "Not remove", Toast.LENGTH_SHORT).show();


                                                    }
                                                });
                                    } else {
                                        Toast.makeText(itemView.getContext(), "Fail", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });



           /* productquantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final  Dialog qtydialog = new Dialog(itemView.getContext());
                    qtydialog.setContentView(R.layout.quantity_dialog);
                    qtydialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    qtydialog.setCancelable(false);
                    final EditText enterqtyNo = qtydialog.findViewById(R.id.q_d_quantity);
                    qut = enterqtyNo.getText().toString();
                    Button canel =  qtydialog.findViewById(R.id.q_d_cancel_btn);
                    Button ok = qtydialog.findViewById(R.id.q_d_ok_btn);

                    firebaseFirestore=FirebaseFirestore.getInstance();
                   // String id = cartItemModelList.get(getAdapterPosition()).getProductid();
                    String id ="3";

                    canel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            qtydialog.dismiss();
                        }
                    });

                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                           productquantity.setText("Qty: " +enterqtyNo.getText());
                          // updateqty(id, qut);
                           qtydialog.dismiss();
                        }
                    });
                    qtydialog.show();
                }
            });
        }
*/
       /* private void updateqty(String id, String q) {

            Map<String,Object> updateqty = new HashMap<>();
            updateqty.put("shoeQty",q);

            //{firebaseFirestore.collection("AddToCart").document("shoes")
            //                .collection("AddedToCart")
            //                .add(cartmap)}
            firebaseFirestore.collection("AddToCart").document("shoes")
                          .collection("AddedToCart")
                    .whereEqualTo("product_ID",id).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && !task.getResult().isEmpty()){
                                //DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                              //  String documentID = documentSnapshot.getId();
                                firebaseFirestore.collection("AddToCart").document("shoes")
                                                .collection("AddedToCart")
                                        .document("AoWF4h5mgGgwqkmVpLhl")
                                        .update(updateqty)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
*//*
                                                if (task.isSuccessful()){
                                                    productquantity.setText("Qty: " +qut);
                                                }
                                                else {
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(itemView.getContext(), error, Toast.LENGTH_SHORT).show();
                                                }*//*
                                                Toast.makeText(itemView.getContext(), "success", Toast.LENGTH_SHORT).show();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(itemView.getContext(), "fail", Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }
                            else {
                                Toast.makeText(itemView.getContext(), "not work", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }*/
        }
    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        private final TextView totalitem;
        private final TextView totalitemprice;
        private final TextView totalamount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalitem = itemView.findViewById(R.id.c_t_a_total_items);
            totalitemprice = itemView.findViewById(R.id.c_t_a_total_items_price);
            totalamount = itemView.findViewById(R.id.c_t_a_total_amount_price);
        }

        private void setTotalAmount(String totalitems, String totalitemprices, String totalamounts) {
            totalitem.setText(totalitems);
            totalitemprice.setText(totalitemprices);
            totalamount.setText(totalamounts);
        }
    }
}
