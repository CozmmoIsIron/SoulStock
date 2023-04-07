package com.example.solestock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
public class DeleteShoeActivity extends AppCompatActivity {
    private EditText shoeName;
    private Button deleteButton;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_shoe);

        // initialize variables
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        shoeName = findViewById(R.id.ShoeN);
        deleteButton = findViewById(R.id.DeleteShoeB);

        // set onclick listener for delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShoe();
            }
        });
    }

    private void deleteShoe() {
        String name = shoeName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(DeleteShoeActivity.this, "Please enter a shoe name", Toast.LENGTH_SHORT).show();
            return;
        }

        // replace email dot with comma for firebase key
        String userEmail = currentUser.getEmail().replace(".", ",");

        // get reference to user's sneakers and delete shoe with matching name
        databaseReference.child(userEmail).child("Sneakers")
                .orderByChild("shoeName")
                .equalTo(name)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                snapshot.getRef().removeValue();
                            }
                            Toast.makeText(DeleteShoeActivity.this, name + " has been deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DeleteShoeActivity.this, name + " not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(DeleteShoeActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
