package com.example.tripplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tripplanner.adapters.CategoriesAdapter;
import com.example.tripplanner.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        binding.mallsRv.setAdapter(new CategoriesAdapter());
//        binding.malls2Rv.setAdapter(new CategoriesAdapter());
//        binding.malls3Rv.setAdapter(new CategoriesAdapter());

        getPlaces("Religious Sites", binding.religiousSitesRv);

        getPlaces("Malls", binding.mallsRv);

        getPlaces("Monuments", binding.monumentsRv);

        getPlaces("Restaurant", binding.restaurantRv);

        getPlaces("Parks", binding.parksRv);

        getPlaces("Entertainment", binding.entertainmentRv);

        getPlaces("Historical", binding.historicalRv);
    }

    private void getPlaces(String category, RecyclerView recyclerView) {
        firestore.collection("places").whereEqualTo("category", category)
                .limit(10).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<Place> places = new ArrayList<>();
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    Place place = snapshot.toObject(Place.class);
                    places.add(place);
                }
                recyclerView.setAdapter(new CategoriesAdapter(places));
            }
        });
    }

    public void addPlan(View view) {
        startActivity(new Intent(this, AddPlanActivity.class));
    }

    public void openAllPlaces(String category) {
        startActivity(new Intent(this, AllCategoriesActivity.class)
                .putExtra("category", category));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void openReligiousSites(View view) {
        openAllPlaces("Religious Sites");
    }

    public void openMalls(View view) {
        openAllPlaces("Malls");

    }

    public void openMonuments(View view) {
        openAllPlaces("Monuments");

    }

    public void openRestaurants(View view) {
        openAllPlaces("Restaurant");
    }

    public void openParks(View view) {
        openAllPlaces("Parks");
    }

    public void openEntertainments(View view) {
        openAllPlaces("Entertainment");
    }

    public void openHistorical(View view) {
        openAllPlaces("Historical");
    }
}