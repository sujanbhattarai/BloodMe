package com.e.bloodme;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.Login.LoginFragment;


public class MainActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth = FirebaseAuth.getInstance();
//
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
//
//    public void  updateUI(FirebaseUser account){
//        if(account != null){
//            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
//            setContentView(R.layout.afterlogin);
//        }else {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new LoginFragment()).commit();
}
}