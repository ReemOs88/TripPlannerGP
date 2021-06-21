package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripplanner.adapters.ItemRate;
import com.example.tripplanner.databinding.ActivityAddReviewBinding;

public class AddReviewActivity extends AppCompatActivity {
    ActivityAddReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);
    }

    public void getDataFromUi(View view) {
        float stars = binding.ratingBar.getRating();

        if (stars == 0) {
            Toast.makeText(this, "Rate must be bigger than zero", Toast.LENGTH_LONG).show();
            return;
        }

        String comment = binding.etComment.getText().toString();

        if (comment.isEmpty()) {
            Toast.makeText(this, "Comment required", Toast.LENGTH_LONG).show();
            return;
        }

        ItemRate rateRequest = new ItemRate("Amir", stars, comment);

        rateProvider(rateRequest);
    }

    private void rateProvider(ItemRate rateRequest) {

    }

}