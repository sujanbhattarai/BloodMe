package com.e.bloodme;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class blooddonate extends AppCompatActivity {
    EditText name, age, blood, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blooddonor);
        Button donateBtn = (Button) findViewById(R.id.donate);
        Button cancelBtn = (Button) findViewById(R.id.request);
        name = findViewById(R.id.editText3);
        age = findViewById(R.id.editText5);
        blood = findViewById(R.id.editText6);
        address = findViewById(R.id.editText8);
        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean end = validateForm();
                if(end==true) {
                    Intent startIntent = new Intent(getApplicationContext(), blood_requester.class);
                    startActivity(startIntent);
                } else {
                    toastMessage("Please complete the form");
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endIntent = new Intent(getApplicationContext(), blood_requester.class);
                startActivity(endIntent);
            }
        });



    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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

//    public static boolean isaddress(String add) {
//        if (TextUtils.isEmpty(add)) {
//            return false;
//        } else {
//            return true;
//        }
//    }
    private boolean validateForm() {
        boolean valid = true;
        String name1 = name.getText().toString();
        if (TextUtils.isEmpty(name1)) {
            name.setError("Required.");
            valid = false;
        } else {
            name.setError(null);
        }

        String age1 = age.getText().toString();

        if (TextUtils.isEmpty(age1)) {
            age.setError("Required.");
            valid = false;
        } else {
            age.setError(null);
        }

        String blood1 = blood.getText().toString();
        if (TextUtils.isEmpty(name1)) {
            blood.setError("Required.");
            valid = false;
        } else {
            blood.setError(null);
        }

        String[] array = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for(int i=0; i< array.length; i++) {
            if(!array[i].equalsIgnoreCase(blood1)) {
                blood.setError("Invalid.");
                valid = false;
            }
        }


        String address1 = address.getText().toString();
        if (TextUtils.isEmpty(name1)) {
            address.setError("Required.");
            valid = false;
        } else {
            address.setError(null);
        }
        return valid;
    }


}
