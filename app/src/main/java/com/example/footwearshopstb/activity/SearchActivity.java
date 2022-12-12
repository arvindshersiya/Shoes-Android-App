package com.example.footwearshopstb.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footwearshopstb.R;
import com.example.footwearshopstb.adapter.HorizontalProductScrollAdapter;
import com.example.footwearshopstb.model.HorizontalProductScrollModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    TextView notfound;
    SearchView searchView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        notfound = findViewById(R.id.notfound_text);
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.search_recycle);


        recyclerView.setVisibility(View.VISIBLE);

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
        final List<String> ids = new ArrayList<>();

        Adapter adapter = new Adapter(horizontalProductScrollModelList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                horizontalProductScrollModelList.clear();
                ids.clear();
                String[] tags = query.toLowerCase().split(" ");
                for (String tag : tags) {
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("SearchProduct")
                            .whereArrayContains("tags", tag)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {

                                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                            HorizontalProductScrollModel horizontalProductScrollModel =
                                                    new HorizontalProductScrollModel
                                                            (documentSnapshot.get("product_ID").toString(),
                                                                    documentSnapshot.get("product_image").toString(),
                                                                    documentSnapshot.get("product_title").toString(),
                                                                    documentSnapshot.get("product_subtitle").toString(),
                                                                    documentSnapshot.get("product_price"),
                                                                    documentSnapshot.get("product_des").toString());
                                            if (!ids.contains(horizontalProductScrollModel.getProduct_ID())) {
                                                horizontalProductScrollModelList.add(horizontalProductScrollModel);
                                                ids.add(horizontalProductScrollModel.getProduct_ID());

                                            }
                                        }
                                        if (tag.equals(tags[tags.length - 1])) {
                                            if (horizontalProductScrollModelList.size() == 0) {
                                                notfound.setVisibility(View.VISIBLE);
                                                recyclerView.setVisibility(View.GONE);
                                            } else {
                                                notfound.setVisibility(View.GONE);
                                                recyclerView.setVisibility(View.VISIBLE);
                                                adapter.getFilter().filter(query);
                                            }

                                        }

                                    } else {
                                        String e = task.getException().getMessage();
                                        Toast.makeText(SearchActivity.this, e, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                horizontalProductScrollModelList.clear();
                ids.clear();
                return false;
            }
        });

    }

    class Adapter extends HorizontalProductScrollAdapter implements Filterable {

        public Adapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
            super(horizontalProductScrollModelList);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    return null;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    notifyDataSetChanged();
                }
            };
        }
    }
}