package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripplanner.adapters.ItemRate;
import com.example.tripplanner.databinding.ActivityAddReviewBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddReviewActivity extends AppCompatActivity {
    ActivityAddReviewBinding binding;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    String placeId;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_review);

        placeId = getIntent().getStringExtra("placeId");

        getUserData();
    }

    private void getUserData() {
        firestore.collection("users").document(auth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(documentSnapshot -> {
                    username = documentSnapshot.get("username").toString();
                    System.out.println("USERNAME : " + username);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
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

        ItemRate rateRequest = new ItemRate(username, stars, comment);

        rateProvider(rateRequest);
    }

    private void rateProvider(ItemRate rateRequest) {
        firestore.collection("places").document(placeId)
                .collection("reviews").document().set(rateRequest)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Review added", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}