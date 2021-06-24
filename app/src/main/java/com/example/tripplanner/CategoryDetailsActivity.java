package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripplanner.adapters.ItemRate;
import com.example.tripplanner.adapters.RatesAdapter;
import com.example.tripplanner.adapters.SliderAdapter;
import com.example.tripplanner.databinding.ActivityCategoryDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsActivity extends AppCompatActivity {
    ActivityCategoryDetailsBinding binding;
    List<ItemRate> itemRates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_details);

        binding.imageSlider.setSliderAdapter(new SliderAdapter(this));


        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));
        itemRates.add(new ItemRate("Amir Mohammed", 4, "Amazing mall"));

        binding.reviewsRv.setAdapter(new RatesAdapter(itemRates));
    }

    public void openMaps(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void addReview(View view) {
        startActivity(new Intent(this, AddReviewActivity.class));
    }

}