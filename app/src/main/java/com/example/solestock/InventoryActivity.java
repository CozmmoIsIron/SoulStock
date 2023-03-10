package com.example.solestock;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Log extends AppCompatActivity {
    List<User> fetchData;

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    int count = 0;
    Button add, commit, sort, Inverse, delete, deletebtn, date;
    AlertDialog builderAlert;
    Context context;
    LayoutInflater layoutInflater;
    DatabaseReference database;
    Locale id = new Locale("in", "ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-YYYY", id);
    View showInput;
    TextView tv_name, dt_name;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference();
    DatabaseReference reference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Activities");
        add = findViewById(R.id.buttonadd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        context = this;
        delete = findViewById(R.id.buttondelete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builderAlert = new AlertDialog.Builder(context).create();
                layoutInflater = getLayoutInflater();
                showInput = layoutInflater.inflate(R.layout.delete_layout, null);
                builderAlert.setView(showInput);
                dt_name = showInput.findViewById(R.id.delete_name);

                deletebtn = (Button) showInput.findViewById(R.id.delete);
                builderAlert.show();
                deletebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = dt_name.getText().toString().trim();
                        if (!name.isEmpty()) {
                            deleteData(name);
                        } else
                            Toast.makeText(Log.this, "Field is Required", Toast.LENGTH_SHORT).show();

                    }

                    private void deleteData(String name) {
                        reference = FirebaseDatabase.getInstance().getReference("Activities");
                        reference.child(name).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Log.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    tv_name.setText("");
                                    builderAlert.cancel();
                                } else {
                                    Toast.makeText(Log.this, "Failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });

            }
        });


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) return;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    User du = new User();
                    du.setName(ds.child("name").getValue(String.class));
                    du.setPrice(ds.child("duration").getValue(String.class));


                    fetchData.add(du);
                }


                myAdapter = new MyAdapter(fetchData);
                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

