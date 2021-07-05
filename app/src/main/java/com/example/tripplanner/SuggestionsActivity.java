package com.example.tripplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final EditText edittext = new EditText(this);
//        alert.setMessage("Enter Your Message");
        alert.setTitle("Enter Your Plan Name");

        alert.setView(edittext);

        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value
                //OR
                String name = edittext.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("places", places);
                map.put("id", planId);
                map.put("name", name);

                firestore.collection("users").document(auth.getCurrentUser().getUid())
                        .collection("myPlans").document(planId).set(map)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(SuggestionsActivity.this, "Plan saved", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(SuggestionsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();


    }


}

