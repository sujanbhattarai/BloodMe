package com.e.bloodme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.blooddonor);

        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), thankyou.class);
                startActivity(startIntent);
            }
        });

        setContentView(R.layout.activity_main);

    }
}