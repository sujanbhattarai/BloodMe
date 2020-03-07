package com.e.bloodme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.Login.LoginFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
}
}