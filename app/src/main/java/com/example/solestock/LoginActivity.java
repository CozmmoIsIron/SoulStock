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

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginButton;
    TextView mCreate;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        mEmail= findViewById(R.id.emails);
        mPassword= findViewById(R.id.password);
        mLoginButton=findViewById(R.id.loginb);
        mCreate= findViewById(R.id.signuptext);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString().trim();
                String  pass= mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");   // if email or pass is emmty reset focus
                    mEmail.requestFocus();
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
                    return;}

                fAuth=FirebaseAuth.getInstance();
                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent((getApplicationContext()),MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this,"Login Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}
