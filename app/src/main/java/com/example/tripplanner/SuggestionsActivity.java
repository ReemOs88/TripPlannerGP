package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tripplanner.adapters.SuggestionsAdapter;
import com.example.tripplanner.databinding.ActivitySuggestionsBinding;

import java.util.ArrayList;

public class SuggestionsActivity extends AppCompatActivity {
    ActivitySuggestionsBinding binding;
    ArrayList<Place> places = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_suggestions);

        places = getIntent().getParcelableArrayListExtra("finalPlaces");

        binding.suggestionsRv.setAdapter(new SuggestionsAdapter(places));
    }

}

