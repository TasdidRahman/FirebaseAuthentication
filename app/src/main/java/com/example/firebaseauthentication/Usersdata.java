package com.example.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Usersdata extends AppCompatActivity {

    private ListView list_usersData;

    DatabaseReference databaseReference;
    private List<HandleDatabase> handleDatabaseList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersdata);

        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        handleDatabaseList = new ArrayList<>();
        customAdapter= new CustomAdapter(this,handleDatabaseList);

        list_usersData = findViewById(R.id.list_usersData);

    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                handleDatabaseList.clear();


                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    HandleDatabase handleDatabase = dataSnapshot1.getValue(HandleDatabase.class);

                    handleDatabaseList.add(handleDatabase);
                }

                list_usersData.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
