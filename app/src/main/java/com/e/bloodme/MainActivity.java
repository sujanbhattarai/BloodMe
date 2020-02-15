package com.e.bloodme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


import com.e.bloodme.Login.LoginFragment;


public class MainActivity extends AppCompatActivity {
    Button btn, sup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
 }
}