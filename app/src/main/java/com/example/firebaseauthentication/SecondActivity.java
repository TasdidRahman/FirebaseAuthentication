package com.example.firebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtAge;
    private Button btnLogout;
    private Button btnSaveData;
    private Button btnShowData;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");


        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);

        btnLogout = findViewById(R.id.btnLogout);
        btnSaveData = findViewById(R.id.btnSaveData);
        btnShowData = findViewById(R.id.btnShowData);

        btnLogout.setOnClickListener(this);
        btnSaveData.setOnClickListener(this);
        btnShowData.setOnClickListener(this);


    }

    private void saveData() {

        String name = edtName.getText().toString().trim();
        String age = edtAge.getText().toString().trim();

        String key = databaseReference.push().getKey();

        HandleDatabase handleDatabase = new HandleDatabase(name, age);

        databaseReference.child(key).setValue(handleDatabase);
        Toast.makeText(this, "User name and age added", Toast.LENGTH_LONG).show();

        edtName.setText("");
        edtAge.setText("");


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signOut();
    }

    private void signOut() {

        mAuth.signOut();
        finish();
        Toast.makeText(SecondActivity.this, "Signned out",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnLogout:
                signOut();
                break;
            case R.id.btnSaveData:
                saveData();
                break;

            case R.id.btnShowData:
                Intent intent = new Intent(this, Usersdata.class);
                startActivity(intent);
                break;

        }
    }


}
