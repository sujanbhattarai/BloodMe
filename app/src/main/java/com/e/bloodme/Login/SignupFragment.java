package com.e.bloodme.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.e.bloodme.R;
import com.e.bloodme.afterlogin;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupFragment extends AppCompatActivity {
    TextInputEditText email, password, first, last, textInputEditText, textInputEditText2 ;
    EditText dob, mobile;
    Button register, homebutton;
    User user;

    public static final String TAG = "YOUR-TAG-NAME";

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params){

        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://108.225.65.61/useraccount/register/doregister",params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {

                try {
                    // JSON Object
                    JSONObject obj = new JSONObject(response);
                    // When the JSON response has status boolean value assigned with true
                    if(obj.getBoolean("status")){
                        // Display successfully registered message using Toast
                        Toast.makeText(getApplicationContext(), "You are successfully registered!", Toast.LENGTH_LONG).show();
                        Intent login = new Intent(getApplicationContext(), afterlogin.class);
                        startActivity(login);
                    }
                    // Else display error message
                    else{
                        toastMessage("Error");
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {

                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
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
                // Instantiate Http Request Param Object
                RequestParams params = new RequestParams();
                if(end == true) {
                    user.setEmail(email.getText().toString());
                    user.setFirst(first.getText().toString());
                    user.setLast(last.getText().toString());
                    user.setDOB(dob.getText().toString());
                    user.setMobile(mobile.getText().toString());
                    user.setPassword(password.getText().toString());

                    // Put Http parameter username with value of Email Edit View control
                    params.put("username", email);
                    // Put Http parameter password with value of Password Edit View control
                    params.put("password", password);
                    // Invoke RESTful Web Service with Http parameters
                    invokeWS(params);
                }
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