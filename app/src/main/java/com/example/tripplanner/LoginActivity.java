package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }


    public void forgetPassword(View view) {
        startActivity(new Intent(this, ForgetPasswordActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }


}