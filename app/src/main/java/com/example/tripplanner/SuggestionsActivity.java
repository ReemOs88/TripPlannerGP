package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tripplanner.adapters.SuggestionsAdapter;
import com.example.tripplanner.databinding.ActivitySuggestionsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SuggestionsActivity extends AppCompatActivity {
    ActivitySuggestionsBinding binding;
    public static ArrayList<Place> places = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    String planId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_suggestions);

        planId = String.valueOf(System.currentTimeMillis());

//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        places = bundle.getParcelableArrayList("finalPlaces");

        binding.suggestionsRv.setAdapter(new SuggestionsAdapter(places));

        if (getIntent().hasExtra("canSave")) {
            binding.savePlan.setVisibility(View.VISIBLE);
        } else {
            binding.savePlan.setVisibility(View.GONE);
        }
    }

    public void savePlan(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("places", places);
        map.put("id", planId);

        firestore.collection("users").document(auth.getCurrentUser().getUid())
                .collection("myPlans").document(planId).set(map)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Plan saved", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });
    }


}

