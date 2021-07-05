package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tripplanner.adapters.MyPlansAdapter;
import com.example.tripplanner.databinding.ActivityMyPlansBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MyPlansActivity extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    List<MyPlan> myPlans = new ArrayList<>();

    ActivityMyPlansBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_plans);

        getMyPlans();
    }


    private void getMyPlans() {
        firestore.collection("users").document(auth.getCurrentUser().getUid())
                .collection("myPlans").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        MyPlan myPlan = snapshot.toObject(MyPlan.class);
                        myPlans.add(myPlan);
                    }

                    binding.myPlansRv.setAdapter(new MyPlansAdapter(myPlans));
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });

    }

}