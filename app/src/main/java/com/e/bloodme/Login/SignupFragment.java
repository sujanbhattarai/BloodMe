package com.e.bloodme.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.e.bloodme.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFragment extends Fragment {
    TextInputEditText email, password, first, last;
    EditText dob, mobile;
    Button register, homebutton;
    DatabaseReference reff;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, null);
        email = view.findViewById(R.id.reg_email);
        password = view.findViewById(R.id.reg_password);
        dob = view.findViewById(R.id.reg_dob);
        first = view.findViewById(R.id.reg_first);
        last = view.findViewById(R.id.reg_last);
        mobile = view.findViewById(R.id.reg_mobile);
        register = view.findViewById(R.id.btn_register);
        homebutton = view.findViewById(R.id.homebutton);
        user = new User();
        reff = FirebaseDatabase.getInstance().getReference().child("User");
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setEmail(email.getText().toString());
                user.setFirst(first.getText().toString());
                user.setLast(last.getText().toString());
                user.setDOB(dob.getText().toString());
                user.setMobile(mobile.getText().toString());
                user.setPassword(password.getText().toString());
                reff.push().setValue(user);
                Toast.makeText(getContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
            }
        });
        return view;
    }
}