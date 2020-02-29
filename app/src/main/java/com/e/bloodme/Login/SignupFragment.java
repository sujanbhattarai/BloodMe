package com.e.bloodme.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.e.bloodme.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupFragment extends Fragment {
    TextInputEditText email, password, first, last, textInputEditText, textInputEditText2 ;
    EditText dob, mobile;
    Button register, homebutton;
    User user;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message").child("User");


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
                myRef.push().setValue(user);
                Toast.makeText(getContext(), "Successfully registered", Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    //User is signed in
                    Log.d("EmailPassword", "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully Signed In with " + user.getEmail());

                }else{
                    Log.d("EmailPassword", "onAuthStateChanged:signed_out");
                    toastMessage("Successfully Signed Out ");

                }
            }
        };


        return view;
    }
    private void toastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    private boolean validateForm() {
        boolean valid = true;

        String em = email.getText().toString();
        if (TextUtils.isEmpty(em)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }


        String pass = password.getText().toString();
        if (TextUtils.isEmpty(pass)) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}