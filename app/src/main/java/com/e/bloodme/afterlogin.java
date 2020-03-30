package com.e.bloodme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.Login.LoginFragment;
import com.google.firebase.auth.FirebaseAuth;

public class afterlogin extends AppCompatActivity {
    Button request, donate, exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afterlogin);

        request = (Button) findViewById(R.id.Request);
        donate = (Button) findViewById(R.id.Donate);
        exit = (Button)findViewById(R.id.Exit);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), blooddonate.class);
                startActivity(startIntent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), blood_requester.class);
                startActivity(startIntent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent startIntent = new Intent(getApplicationContext(), LoginFragment.class);
                startActivity(startIntent);
            }
        });

    }
}

