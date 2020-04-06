package com.e.bloodme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.e.bloodme.Login.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_psw);
        Button forgetPass = findViewById(R.id.button2);
        final Button cancel_pass = findViewById(R.id.cancel_button);
        forgetPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText reemail = findViewById(R.id.editText);
                String Editemail = reemail.getText().toString();
                if(Editemail.isEmpty()){
                    toastMessage("Enter the Email Address");

                }else {

                    auth.sendPasswordResetEmail(reemail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // do something when mail was sent successfully.
                                        toastMessage("Email has been Sent for reset password");
                                    } else {
                                        // ...
                                        toastMessage("Invalid Email Address");
                                    }
                                }
                            });
                }
            }
            });
        cancel_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endIntent = new Intent(forgotpassword.this, blood_requester.class);
                startActivity(endIntent);
            }
        });

    }
}