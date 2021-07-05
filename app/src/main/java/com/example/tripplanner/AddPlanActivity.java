package com.example.tripplanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.security.identity.DocTypeNotSupportedException;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tripplanner.databinding.ActivityAddPlanBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddPlanActivity extends AppCompatActivity {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public static ArrayList<Place> places = new ArrayList<>();
    ActivityAddPlanBinding binding;
    List<String> categories = new ArrayList<>();

    String[] locations = {"Cairo", "Alexandria"};
    String[] days = {"1 day", "2 days", "3 days", "4 days", "5 days", "6 days", "7 days"};
    String[] hours = {"1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "6 hours", "7 hours", "8 hours"};

    String location = "";
    int day = 0;
    int hour = 0;

    int totalTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plan);

        clicks();

        ArrayAdapter<String> locationsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, locations);
        binding.menuWhereTo.setAdapter(locationsAdapter);

        binding.menuWhereTo.setOnItemClickListener((parent, view, position, id) -> location = locations[position]);

        ArrayAdapter<String> daysAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, days);
        binding.menuDays.setAdapter(daysAdapter);

        binding.menuDays.setOnItemClickListener((parent, view, position, id) -> day = (position + 1));

        ArrayAdapter<String> hoursAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, hours);
        binding.menuHours.setAdapter(hoursAdapter);

        binding.menuHours.setOnItemClickListener((parent, view, position, id) -> hour = (position + 1));

    }

    private void clicks() {
        binding.chipReligion.setOnClickListener(v -> {
            addRemoveCategory("Religious Sites");
        });

        binding.chipMall.setOnClickListener(v -> {
            addRemoveCategory("Malls");
        });

        binding.chipMonuments.setOnClickListener(v -> {
            addRemoveCategory("Monuments");
        });

        binding.chipRestaurant.setOnClickListener(v -> {
            addRemoveCategory("Restaurant");
        });

        binding.chipPark.setOnClickListener(v -> {
            addRemoveCategory("Parks");
        });

        binding.chipEntertainment.setOnClickListener(v -> {
            addRemoveCategory("Entertainment");
        });

        binding.chipHistorical.setOnClickListener(v -> {
            addRemoveCategory("Historical");
        });
    }


    private void addRemoveCategory(String category) {
        if (categories.contains(category)) {
            categories.remove(category);
        } else {
            categories.add(category);
        }

        Log.i("TAG", "addRemoveCategory: " + categories.size());
    }

    public void getSuggestionPlan(View view) {

        if (location.isEmpty()) {
            Toast.makeText(this, "Select location", Toast.LENGTH_SHORT).show();
            return;
        }

        if (day == 0) {
            Toast.makeText(this, "Select days", Toast.LENGTH_SHORT).show();
            return;
        }

        if (hour == 0) {
            Toast.makeText(this, "Select hours", Toast.LENGTH_SHORT).show();
            return;
        }

        if (categories.isEmpty()) {
            Toast.makeText(this, "Select categories", Toast.LENGTH_SHORT).show();
            return;
        }

        totalTime = day * hour;
        Log.i("TAG", "getSuggestionPlan: TOTAL TIME : " + totalTime);

        getPlaces();
    }

//    private void getPlaces() {
//        List<Place> allPlaces = new ArrayList<>();
//        for (int index = 0; index < categories.size(); index++) {
//            int finalIndex = index;
//            firestore.collection("places")
//                    .whereEqualTo("category", categories.get(index))
//                    .get()
//                    .addOnSuccessListener(queryDocumentSnapshots -> {
//                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
//                            Place place = snapshot.toObject(Place.class);
//                            allPlaces.add(place);
//                        }
//
//                        if (finalIndex == (categories.size() - 1)) {
//                            arrangePlacesAccordingRate(allPlaces);
//                        }
//                    })
//                    .addOnFailureListener(e -> {
//                        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                    });
//        }
//    }

    private void getPlaces() {
        List<Place> allPlaces = new ArrayList<>();
        firestore.collection("places")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        Place place = snapshot.toObject(Place.class);
                        if (place != null && categories.contains(place.getCategory())) {
                            allPlaces.add(place);
                        }
                    }

                    arrangePlacesAccordingRate(allPlaces);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void arrangePlacesAccordingRate(List<Place> allPlaces) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collections.sort(allPlaces, Comparator.comparing(Place::getRate));
            Collections.reverse(allPlaces);
            filterPlanAccordingTime(allPlaces);
        }
    }

    private void filterPlanAccordingTime(List<Place> arrangedPlaces) {
        ArrayList<Place> finalPlaces = new ArrayList<>();
        int restaurantCounter = 0;
        int timeCounter = 0;
        for (Place place : arrangedPlaces) {
            timeCounter += place.getDuration();

            if (timeCounter > totalTime) break;

            if (categories.size() == 1 && categories.contains("Restaurant")){
                finalPlaces.add(place);
                continue;
            }

            if (place.getCategory().equals("Restaurant")) {
                if (restaurantCounter < day) {
                    finalPlaces.add(place);
                    restaurantCounter++;
                }

            } else {
                finalPlaces.add(place);

            }
        }

        navigateToShowPlan(finalPlaces);
    }

    private void navigateToShowPlan(ArrayList<Place> finalPlaces) {
        places = finalPlaces;
        Intent intent = new Intent(this, SuggestionsActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("finalPlaces", finalPlaces);
//        intent.putExtra("bundle", bundle);
        intent.putExtra("canSave", true);
        startActivity(intent);
        finish();
    }

}