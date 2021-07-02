package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tripplanner.databinding.LoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    LoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login);

//        List<String> images = new ArrayList<>();
//        images.add("");
//        images.add("");
//        images.add("");
//        images.add("");
//        images.add("");
//
//        for (int i = 0; i < 140; i++) {
//            String id = String.valueOf(i);
//
//            Place place = new Place(
//                    id,
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    0,
//                    0,
//                    images
//            );
//
//            firestore.collection("places").document(id).set(place);
//        }

    }

    public void forgetPassword(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    public void login(View view) {
        String email = binding.etLoginEmail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(this, "Email required", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = binding.etLoginPassword.getText().toString().trim();

        if (password.isEmpty()) {
            Toast.makeText(this, "Password required", Toast.LENGTH_LONG).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                });

    }


    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


}