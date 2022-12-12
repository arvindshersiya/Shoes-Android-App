package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.bumptech.glide.Glide;
import com.example.footwearshopstb.AddressManager;
import com.example.footwearshopstb.R;
import com.example.footwearshopstb.model.CartItemModel;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ProductDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buybtn, cartbtn;
    ImageView productDe, qtyminus, qtyplus;
    TextView productTi, productpri, subproducttitle, qty_set, productDes;
    long totalQyanity = 1;
    long totalAmount = 0;
    long price = 0;
    List<CartItemModel> cartItemModelList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TabLayout size;
    String getSize = "6";
    static String getOrderId = "";
    HorizontalProductScrollModel horizontalProductScrollModel = null;


    /* Spinner qtySpinner;
     int[] qty ={1,2,3,4,5,6,7,8,9,10};
     int amount = 599;
     int getqty = 1;
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbar = findViewById(R.id.p_d_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productDe = findViewById(R.id.product_details_image);
        productTi = findViewById(R.id.product_details_title);
        productpri = findViewById(R.id.product_details_price);
        subproducttitle = findViewById(R.id.product_details_category);
        qtyminus = findViewById(R.id.p_d_qty_minus);
        qtyplus = findViewById(R.id.imageView4);
        qty_set = findViewById(R.id.p_d_set_qty);
        productDes = findViewById(R.id.product_details_textview);
        //qtySpinner = findViewById(R.id.product_details_qty_spinner);
        size = findViewById(R.id.table_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        qtyminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQyanity > 1) {
                    totalQyanity--;
                    qty_set.setText("" + totalQyanity);


                }

            }

        });

        qtyplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQyanity < 10) {
                    totalQyanity++;
                    qty_set.setText("" + totalQyanity);

                }
            }
        });

       /* ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, Collections.singletonList(qty));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(adapter);
*/

        final Object object = getIntent().getSerializableExtra("id");

        if (object instanceof HorizontalProductScrollModel) {

            horizontalProductScrollModel = (HorizontalProductScrollModel) object;
        }


        buybtn = findViewById(R.id.p_d_buy_btn);
        cartbtn = findViewById(R.id.p_d_cart_btn);


        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);

                if (check) {

                    addToCart();


                } else {

                    Dialog singindialog = new Dialog(ProductDetailsActivity.this);
                    singindialog.setContentView(R.layout.sign_dialog);
                    singindialog.setCancelable(true);

                    singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                    Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                    dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
                            overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                            startActivity(intent);

                        }
                    });
                    dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(ProductDetailsActivity.this, SignUpActivity.class);
                            overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                            startActivity(intent);

                        }
                    });

                    singindialog.show();


                }

                //cartItemModelList.add(new CartItemModel(0,productDe.toString(),productTi.toString(),productpri.toString(),1));
//                addToCart();


            }
        });

        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = sh.getBoolean("flag", false);

                if (check) {
                    AddressManager.chekAcitycity = 0;
                    AddressManager.check_Cart_Buy_btn = 2;
                    buyBtn();
                    Intent intent = new Intent(ProductDetailsActivity.this, AddressAddActivity.class);
                    startActivity(intent);

                } else {

                    Dialog singindialog = new Dialog(ProductDetailsActivity.this);
                    singindialog.setContentView(R.layout.sign_dialog);
                    singindialog.setCancelable(true);

                    singindialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    Button dialogSignInbtn = singindialog.findViewById(R.id.dialog_signin_btn);
                    Button dialogSignUpbtn = singindialog.findViewById(R.id.dialog_signup_btn);

                    dialogSignInbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(ProductDetailsActivity.this, LoginActivity.class);
                            overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                            startActivity(intent);

                        }
                    });
                    dialogSignUpbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            singindialog.dismiss();
                            Intent intent = new Intent(ProductDetailsActivity.this, SignUpActivity.class);
                            overridePendingTransition(R.anim.from_left, R.anim.fromout_right);
                            startActivity(intent);

                        }
                    });

                    singindialog.show();


                }


               /* Intent intent = new Intent(ProductDetailsActivity.this, AddressAddActivity.class);
                startActivity(intent);*/
            }
        });

        String img = horizontalProductScrollModel.getProductimage();
        //   String id = horizontalProductScrollModel.getProduct_ID();


        if (horizontalProductScrollModel != null) {
            Glide.with(getApplicationContext()).load(img).into(productDe);
            productTi.setText(horizontalProductScrollModel.getProducttitle());
            productpri.setText("₹" + horizontalProductScrollModel.getProductprice() + "/-");
            subproducttitle.setText(horizontalProductScrollModel.getProductcategory());
            productDes.setText(horizontalProductScrollModel.getProductdescription());
            price = (long) horizontalProductScrollModel.getProductprice();


        }


        size.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 0:
                        getSize = "6";
                        return;
                    case 1:
                        getSize = "7";
                        return;
                    case 2:
                        getSize = "8";
                        return;
                    case 3:
                        getSize = "9";
                        return;
                    case 4:
                        getSize = "10";
                        return;
                    default:
                        getSize = "0";
                        return;


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

      /*  qtySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 1: amount = amount * qty[1];
                            getqty = qty[1];
                        break;
                    case 2: amount = amount * qty[2];
                        getqty = qty[2];
                        break;
                    case 3: amount = amount * qty[3];
                        getqty = qty[3];
                        break;
                    case 4: amount = amount * qty[4];
                        getqty = qty[4];
                        break;
                    case 5: amount = amount * qty[5];
                        getqty = qty[5];
                        break;
                    case 6: amount = amount * qty[6];
                        getqty = qty[6];
                        break;
                    case 7: amount = amount * qty[7];
                        getqty = qty[7];
                        break;
                    case 8: amount = amount * qty[8];
                        getqty = qty[8];
                        break;
                    case 9: amount = amount * qty[9];
                        getqty = qty[9];
                        break;
                    case 10: amount = amount * qty[10];
                        getqty = qty[10];
                        break;
                    default:
                        return;
                }
            }
        });*/


        String a = new String(generateOrderID(6));
        getOrderId = a;


    }

    private static char[] generateOrderID(int length) {
        //String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
//        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        //   String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        //String combinedChars = capitalCaseLetters + numbers;

        Random random = new Random();
        char[] password = new char[length];


        //   password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        //  password[0] = capitalCaseLetters.charAt(random.nextInt(numbers.length()));
        //   password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[1] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 0; i < length; i++) {
            password[i] = numbers.charAt(random.nextInt(numbers.length()));

        }

        return password;
    }


    private void buyBtn() {
        totalAmount = price * totalQyanity;
        String savecurrentdate, savecurrenttime;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy, HH:mm:ss");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        savecurrentdate = dateFormat.format(calendar.getTime());
        savecurrenttime = timeFormat.format(calendar.getTime());


        //    String id = horizontalProductScrollModel.getProduct_ID();


        //String quantity ="1";


        final HashMap<String, Object> cartmap = new HashMap<>();


        cartmap.put("shoeImage", horizontalProductScrollModel.getProductimage());
        cartmap.put("shoeName", horizontalProductScrollModel.getProducttitle());
        cartmap.put("shoePrice", String.valueOf(horizontalProductScrollModel.getProductprice()));
        cartmap.put("shoeSize", getSize);
        cartmap.put("shoeId", horizontalProductScrollModel.getProduct_ID());
        cartmap.put("shoeQty", String.valueOf(totalQyanity));
        cartmap.put("totalAmount", totalAmount);
        cartmap.put("orderID", getOrderId);


        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("Buybtn")
                .add(cartmap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void addToCart() {
        totalAmount = price * totalQyanity;
        String savecurrentdate, savecurrenttime;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM dd, yyyy, HH:mm:ss");

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        savecurrentdate = dateFormat.format(calendar.getTime());
        savecurrenttime = timeFormat.format(calendar.getTime());


        //    String id = horizontalProductScrollModel.getProduct_ID();


        //String quantity ="1";


        final HashMap<String, Object> cartmap = new HashMap<>();


        cartmap.put("shoeImage", horizontalProductScrollModel.getProductimage());
        cartmap.put("shoeName", horizontalProductScrollModel.getProducttitle());
        cartmap.put("shoePrice", String.valueOf(horizontalProductScrollModel.getProductprice()));
        cartmap.put("shoeSize", getSize);
        cartmap.put("shoeId", horizontalProductScrollModel.getProduct_ID());
        cartmap.put("shoeQty", String.valueOf(totalQyanity));
        cartmap.put("totalAmount", totalAmount);
        cartmap.put("orderID", getOrderId);


        firebaseFirestore.collection("USER")
                .document(AddressManager.user_Id)
                .collection("AddedToCart")
                .add(cartmap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Product add in cart", Toast.LENGTH_SHORT).show();
                            cartbtn.setEnabled(false);
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.top_menu_cart) {
            //  Toast.makeText(ProductDetailsActivity.this, "Clicked Cart", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), MyCartActivity.class);
            startActivity(i);
            finish();
        } else if (item.getItemId() == R.id.top_menu_search) {
            Intent intent = new Intent(ProductDetailsActivity.this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}