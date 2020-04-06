package com.e.bloodme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class blood_requester extends AppCompatActivity {

    EditText name, age, blood, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        name = findViewById(R.id.editText3);
        age = findViewById(R.id.editText5);
        blood = findViewById(R.id.editText6);
        address = findViewById(R.id.editText8);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_requester);
        Button requestBtn = (Button) findViewById(R.id.button);
        Button cancelBtn = (Button) findViewById(R.id.request);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean end = validateForm();
                if(end == true) {
                Intent startIntent = new Intent(getApplicationContext(), requested.class);
                startActivity(startIntent);
            }}
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endIntent = new Intent(getApplicationContext(), afterlogin.class);
                startActivity(endIntent);
            }
        });

    }

    public static boolean isAge(int num) {
        boolean valid = true;

        if(num<1 || num > 100) {
            return false;
        }

        return true;
    }

    public  static String isblood(String blood1) {
        boolean valid = true;
        String[] array = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        String a = "NO";
        for(int i=0; i< array.length; i++) {
            if(!array[i].equalsIgnoreCase(blood1)) {
                 valid = false;
            }else{
                return blood1;
            }
        }
       return "NO";
    }

    public static boolean isaddress(String add) {
        if (TextUtils.isEmpty(add)) {
            return false;
        } else {
            return true;
        }
    }
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isfirst(String m){
        if (TextUtils.isEmpty(m)) {
            return false;
        } else {
            return true;
        }
    }
    private boolean validateForm() {
        boolean valid = true;
        String name1 = name.getText().toString();
        boolean x = isfirst(name1);
        if (x){
            valid = true;
        }else{
            name.setError("Required");
        }

        int age1 = Integer.parseInt(age.getText().toString());

        boolean y = isAge(age1);
        if (y){
            valid = true;
        }else{
            age.setError("Required");
        }

        String blood1 = blood.getText().toString();
        if (TextUtils.isEmpty(name1)) {
            blood.setError("Required.");
            valid = false;
        } else {
            blood.setError(null);
        }
        isblood(blood1);


        String address1 = address.getText().toString();
        boolean z = isaddress(address1);
        if (z){
            valid = true;
        }else{
            address.setError("Required");
        }
        return valid;
    }

}
