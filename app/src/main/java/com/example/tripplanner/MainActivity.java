package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripplanner.adapters.CategoriesAdapter;
import com.example.tripplanner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.mallsRv.setAdapter(new CategoriesAdapter());
        binding.malls2Rv.setAdapter(new CategoriesAdapter());
        binding.malls3Rv.setAdapter(new CategoriesAdapter());
    }


    public void addPlan(View view) {
        startActivity(new Intent(this, AddPlanActivity.class));
    }


    public void allCategories(View view) {
        startActivity(new Intent(this, AllCategoriesActivity.class));
    }


}