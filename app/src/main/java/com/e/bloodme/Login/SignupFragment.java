package com.e.bloodme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.R;
import com.e.bloodme.afterlogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupFragment extends AppCompatActivity {
    TextInputEditText email, password, first, last, textInputEditText, textInputEditText2 ;
    EditText dob, mobile;
    Button register, homebutton;
    User user;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message").child("User");

    public static final String TAG = "YOUR-TAG-NAME";

    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }

            public char charAt(int index) {
                return '*'; // This is the important part
            }

            public int length() {
                return mSource.length(); // Return default
            }

            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        textInputEditText = findViewById(R.id.textInputEditText);
        dob = findViewById(R.id.reg_dob);
        first =findViewById(R.id.reg_first);
        last = findViewById(R.id.reg_last);
        mobile = findViewById(R.id.reg_mobile);
        register = findViewById(R.id.btn_register);
        homebutton = findViewById(R.id.homebutton);
        user = new User();

        password.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        textInputEditText.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginFragment.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean end = validateForm();
                if(end == true) {
                    user.setEmail(email.getText().toString());
                    user.setFirst(first.getText().toString());
                    user.setLast(last.getText().toString());
                    user.setDOB(dob.getText().toString());
                    user.setMobile(mobile.getText().toString());
                    user.setPassword(password.getText().toString());

                    mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                toastMessage("Successfully registered");
                                FirebaseUser user1 = mAuth.getCurrentUser();
                                String id = myRef.push().getKey();
                                myRef.child(id).setValue(user);
//                                String UID = user1.getUid();
//                                myRef.child(UID).child("User Info").child("Email").setValue(user.getEmail());
//                                myRef.child(UID).child("User Info").child("First Name").setValue(user.getfirst());
//                                myRef.child(UID).child("User Info").child("Last Name").setValue(user.getlast());
//                                myRef.child(UID).child("User Info").child("Date of Birth").setValue(user.getDate());
//                                myRef.child(UID).child("User Info").child("Mobile").setValue(user.getMobile());
                                mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Intent login = new Intent(getApplicationContext(), afterlogin.class);
                                                    startActivity(login);
                                                    toastMessage("Signed In Successfully");
                                                } else {
                                                    String message = task.getException().toString();
                                                    toastMessage("Error");
                                                }

                                            }
                                        });
                            }
                        }
                    });
                }
//                }else{
//                    toastMessage("Please complete the form");
//                }

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

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isfirstname(String name){
        if (TextUtils.isEmpty(name)) {
            return false;
        } else {
            return true;
        }
    }
    public static boolean islastname(String name){
        if (TextUtils.isEmpty(name)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isphoneno(String num) {
        boolean valid = true;
        try {
            long no = Integer.parseInt(num);
            if(num.length()!=10) {
                return false;
            }
        } catch(Exception e) {
        }

        return true;

    }

    public static boolean password(String s) {
        boolean valid = true;

        if(s.length() < 6) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".com")) {
            return true;
        }
        return false;
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
        boolean a = password(pass);

        return valid;
    }
}