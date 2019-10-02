package com.example.firebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity {

    private EditText edtName, edtAge;
    private Button btnLogout;
    private Button btnSaveData;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        
        edtName=findViewById(R.id.edtName);
        edtAge= findViewById(R.id.edtAge);
        
        btnLogout = findViewById(R.id.btnLogout);
        btnSaveData= findViewById(R.id.btnSaveData);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });




    }

    private void saveData() {

        String name= edtName.getText().toString().trim();
        String age= edtAge.getText().toString().trim();

        String key= databaseReference.push().getKey();

        HandleDatabase handleDatabase= new HandleDatabase(name, age);

        databaseReference.child(key).setValue(handleDatabase);
        Toast.makeText(this,"User name and age added", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signOut();
    }

    private void signOut(){

        mAuth.signOut();
        finish();
        Toast.makeText(SecondActivity.this, "Signned out.",
                Toast.LENGTH_SHORT).show();

    }
}
