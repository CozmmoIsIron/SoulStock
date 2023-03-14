package com.example.solestock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;
import android.view.View;
import android.text.TextUtils;
import android.widget.Toast;
public class AddShoeActivity extends AppCompatActivity {
    private EditText ShoeName, category, price;
    private FirebaseAuth firebaseAuth;
    Button AddDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencecat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shoe);
        firebaseAuth = FirebaseAuth.getInstance();
        AddDatabase = findViewById(R.id.AddShoeB);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReferencecat = FirebaseDatabase.getInstance().getReference("Users");
        ShoeName = findViewById(R.id.ShoeN);
        category = findViewById(R.id.shoeC);
        price = findViewById(R.id.shoeP);
        AddDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additem();
            }
        });



    }
    public void additem () {
        String name= ShoeName.getText().toString();
        String cat = category.getText().toString();
        String value = price.getText().toString();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        String finaluser = users.getEmail();
        String resultemail = finaluser.replace(".", ",");
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(cat)&&!TextUtils.isEmpty(value)){

            Sneakers shoes = new Sneakers(name,cat,value);
            databaseReference.child(resultemail).child("Sneakers").setValue(shoes);
            databaseReferencecat.child(resultemail).child("ItemByCategory").child(cat).child(name).setValue(shoes);
            ShoeName.setText("");
            price.setText("");
            category.setText("");
            Toast.makeText(AddShoeActivity.this,ShoeName+" Added",Toast.LENGTH_SHORT).show();
        }
        else{ Toast.makeText(AddShoeActivity.this,"Please Fill all the fields",Toast.LENGTH_SHORT).show();

        }
    }
}




