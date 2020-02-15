package com.e.bloodme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.bloodme.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment{


    TextInputEditText email, password;
    Button login, signup;
    DatabaseReference registeredUsers;
    @Nullable


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in, null);
        email = view.findViewById(R.id.email_input);
        password = view.findViewById(R.id.password_input);
        login = view.findViewById(R.id.login);
        signup = view.findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new SignupFragment()).commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeredUsers = FirebaseDatabase.getInstance().getReference().child("User").child("-LoA1aLc1XjX8LDGGlr0");
                registeredUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email1 = email.getText().toString();
                        String password1 = password.getText().toString();
                        String fEmail = dataSnapshot.child("email").getValue().toString();
                        String fPassword = dataSnapshot.child("password").getValue().toString();
                        if((email1.equals(fEmail)) && (password1.equals(fPassword))){
                            Toast.makeText(getContext(), "Welcome" + fEmail, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        return view;
    }


}
