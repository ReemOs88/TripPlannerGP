package com.example.tripplanner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tripplanner.databinding.ActivityAddPlanBinding;

public class AddPlanActivity extends AppCompatActivity {

    ActivityAddPlanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_plan);

        clicks();
    }

    private void clicks() {
        binding.chipCpp.setOnClickListener(v -> {
            Toast.makeText(this, binding.chipCpp.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }


}