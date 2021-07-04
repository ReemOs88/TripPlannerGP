package com.example.tripplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripplanner.adapters.ItemRate;
import com.example.tripplanner.adapters.RatesAdapter;
import com.example.tripplanner.adapters.SliderAdapter;
import com.example.tripplanner.databinding.ActivityCategoryDetailsBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsActivity extends AppCompatActivity {
    ActivityCategoryDetailsBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    Place place;
    List<ItemRate> itemRates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_details);

        place =  getIntent().getParcelableExtra("place");
        binding.setData(place);

        binding.imageSlider.setSliderAdapter(new SliderAdapter(this, place.getImages()));

//        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
//        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
//        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
//        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));

        getReviews();
    }

    private void getReviews() {
        firestore.collection("places").document(place.getId())
                .collection("reviews").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentSnapshot snapshot : value.getDocuments()) {
                    ItemRate itemRate = snapshot.toObject(ItemRate.class);
                    itemRates.add(itemRate);
                }

                binding.reviewsRv.setAdapter(new RatesAdapter(itemRates));

                if (itemRates.size() > 50) {
                    calculateRate();
                }
            }
        });
    }

    private void calculateRate() {
        float totalRate = 0;

        for (ItemRate itemRate : itemRates) {
            totalRate += itemRate.getRate();
        }

        float averageRate = totalRate / itemRates.size();

        String averageRateText = String.valueOf(averageRate);

        binding.tvAverageRate.setText(averageRateText);
    }

    public void openMaps(View view) {
        startActivity(new Intent(this, MapsActivity.class)
                .putExtra("location", place.getLocation())
                .putExtra("name", place.getName())
        );
    }

    public void addReview(View view) {
        startActivity(new Intent(this, AddReviewActivity.class)
                .putExtra("placeId", place.getId())
        );
    }

}