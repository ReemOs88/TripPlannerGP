package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tripplanner.databinding.ActivityAddPlanBinding;

public class AddPlanActivity extends AppCompatActivity {

    ActivityAddPlanBinding binding;

    String[] locations = {"Cairo", "Alexandria"};
    String[] days = {"1 day", "2 days", "3 days", "4 days", "5 days", "6 days", "7 days"};
    String[] hours = {"1 hour", "2 hours", "3 hours", "4 hours", "5 hours", "6 hours", "7 hours", "8 hours"};
    String[] activities = {"Walking", "Swimming", "Photography"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plan);

        clicks();

        ArrayAdapter<String> locationsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, locations);
        binding.menuWhereTo.setAdapter(locationsAdapter);

        ArrayAdapter<String> daysAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, days);
        binding.menuDays.setAdapter(daysAdapter);

        ArrayAdapter<String> hoursAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, hours);
        binding.menuHours.setAdapter(hoursAdapter);

        ArrayAdapter<String> activitiesAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, activities);
        binding.menuActivities.setAdapter(activitiesAdapter);

    }

    private void clicks() {
        binding.chipCpp.setOnClickListener(v -> {
            Toast.makeText(this, binding.chipCpp.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }


    public void getSuggestionPlan(View view) {
        startActivity(new Intent(this, SuggestionsActivity.class));
    }
}