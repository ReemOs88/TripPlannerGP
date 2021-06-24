package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tripplanner.adapters.SuggestionsAdapter;
import com.example.tripplanner.databinding.ActivitySuggestionsBinding;

public class SuggestionsActivity extends AppCompatActivity {
    ActivitySuggestionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_suggestions);

        binding.suggestionsRv.setAdapter(new SuggestionsAdapter());
    }

}

