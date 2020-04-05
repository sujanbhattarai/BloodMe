package com.e.bloodme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.R;
import com.e.bloodme.requested;

public class afterlogin extends AppCompatActivity {
    Button Donate, request, Exit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.afterlogin);
        Button Donate = (Button) findViewById(R.id.donate);
        Button request = (Button) findViewById(R.id.request);
        //Button Exit = (Button) findViewById(R.id.request);

        Donate.setOnClickListener(new View.OnClickListener() {
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

//        Exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent startIntent = new Intent(getApplicationContext(), blood_donate.class);
//                startActivity(startIntent);
//            }
//        });
    }
}