package com.example.solestock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText mBusiness, mEmail, mPassword;
    Button mSignupButton;
    TextView mLoginTxt;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.emails2);     // Initalizing views
        mPassword = findViewById(R.id.password);
        mLoginTxt = findViewById(R.id.logintext);
        mSignupButton = findViewById(R.id.signup);
        mBusiness = findViewById(R.id.bname);
        mSignupButton.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();
        mLoginTxt.setOnClickListener(new View.OnClickListener() { // on text click
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class)); // go to login page
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String email = mEmail.getText().toString().trim();  // on Signup Click get pass and emaill from edit text
        String pass = mPassword.getText().toString().trim();
        String CompName= mBusiness.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email is required");   // if email or pass is emmty reset focus
            mEmail.requestFocus();
            return;
        }
        if(CompName.isEmpty()){
            mBusiness.setError("Business name required");
            mBusiness.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("Provide a valid email");
            mEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            mPassword.setError("Password is required");
            mPassword.requestFocus();
            return;
        }
        if (pass.length() < 6) { // pass length has to be 6 or more
            mPassword.setError("Password must be 9 characters or more");
            mPassword.requestFocus();
            return;
        }
        fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override      // create account with email and pass
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) { // if signup worked
                    User user= new User(CompName,email);
                    FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Register.this,"User created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent((getApplicationContext()),MainActivity.class)); // start main activty
                                }
                            });


                } else {
                    // why signup failed
                    Toast.makeText(Register.this,"SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}