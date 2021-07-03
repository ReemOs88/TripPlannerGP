package com.example.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tripplanner.adapters.AllCategoriesAdapter;
import com.example.tripplanner.adapters.CategoriesAdapter;
import com.example.tripplanner.databinding.ActivityAllCategoriesBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {
    ActivityAllCategoriesBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_categories);

        category = getIntent().getStringExtra("category");


//        binding.allCategoriesRv.setAdapter(new AllCategoriesAdapter());

        getPlaces(category, binding.allCategoriesRv);
    }

    private void getPlaces(String category, RecyclerView recyclerView) {
        firestore.collection("places").whereEqualTo("category", category)
               .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<Place> places = new ArrayList<>();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Place place = snapshot.toObject(Place.class);
                    places.add(place);
                }
                recyclerView.setAdapter(new AllCategoriesAdapter(places));
            }
        });
    }

}