package com.example.solestock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    EditText mUsername, mEmail, mPassword;
    Button mSignupButton;
    TextView mLoginTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail= findViewById(R.id.emails);     // Initalizing views
        mPassword= findViewById(R.id.password);
        mLoginTxt= findViewById(R.id.logintext);
        mSignupButton=findViewById(R.id.signup);
    }
}