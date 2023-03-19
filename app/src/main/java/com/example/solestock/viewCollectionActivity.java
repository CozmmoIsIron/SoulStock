package com.example.solestock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class viewCollectionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView totalnoofitem, totalnoofsum;
    private int counttotalnoofitem =0;
    private SneakerAdapter mAdapter;
    private String userEmail;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_collection);
        mRecyclerView = findViewById(R.id.recyclerViews);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       String email= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail().replace(".",",");
        totalnoofitem= findViewById(R.id.totalnoitem);
        totalnoofsum = findViewById(R.id.totalsum);



        //System.out.println((FirebaseDatabase.getInstance().getReference().child("Users").child("bob@gmail,com").child("Sneakers")));

        FirebaseRecyclerOptions<Sneakers> options =
                new FirebaseRecyclerOptions.Builder<Sneakers>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").child(email).child("Sneakers"), Sneakers.class)
                        .build();

        mAdapter = new SneakerAdapter(options,totalnoofsum,totalnoofitem);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
