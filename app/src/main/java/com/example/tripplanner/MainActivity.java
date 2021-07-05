package com.example.tripplanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

import mehdi.sakout.aboutpage.AboutPage;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ActivityMainBinding binding;

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);


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

        binding.navView.setNavigationItemSelectedListener(item -> {
            binding.drawer.closeDrawers();

            if (item.getItemId() == R.id.item_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else if (item.getItemId() == R.id.item_about_us) {
                startActivity(new Intent(this, AboutUsActivity.class));

            } else if (item.getItemId() == R.id.item_logout) {
                logout();
            }

            return true;
        });
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        binding.drawer.openDrawer(Gravity.START);
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
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

    public void openFavourite(View view) {
        startActivity(new Intent(this, MyPlansActivity.class));
    }


}