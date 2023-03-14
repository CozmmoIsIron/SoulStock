package com.example.solestock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser user;
    private DatabaseReference reference;
    private  String userID;
    Button Add;
    Button Delete;
    Button InventoryView;
    Button Product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView IntroTextView= (TextView) findViewById(R.id.IntroText);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID= user.getUid();
        Add = findViewById(R.id.addb);
        Delete = findViewById(R.id.deleteButton);
        Product =  findViewById(R.id.viewButton);
       InventoryView = findViewById(R.id.InventoryB);

       Add.setOnClickListener(this);




        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile=snapshot.getValue(User.class);
                if(userprofile!=null){
                    String name= userprofile.CompanyName;
                    IntroTextView.setText("Welcome Back "+ name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                  Toast.makeText(MainActivity.this,"Error", Toast.LENGTH_LONG).show();
            }
        });

        

    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.addb : i = new Intent(this,AddShoeActivity.class); startActivity(i); break;
            default: break;
        }

    }
}