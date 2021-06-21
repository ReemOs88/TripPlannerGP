package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tripplanner.adapters.AllCategoriesAdapter;
import com.example.tripplanner.adapters.CategoriesAdapter;
import com.example.tripplanner.databinding.ActivityAllCategoriesBinding;

public class AllCategoriesActivity extends AppCompatActivity {
    ActivityAllCategoriesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_categories);

        binding.allCategoriesRv.setAdapter(new AllCategoriesAdapter());


    }


}