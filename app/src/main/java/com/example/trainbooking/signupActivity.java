package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupActivity extends AppCompatActivity {

    private EditText mfullName, mhomeCity, mphone, memail, mpassword;
    private TextView mlogint;
    private Button msignUp;

    private FirebaseAuth auth;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Users");

        mfullName = findViewById(R.id.fullname);
        mhomeCity = findViewById(R.id.homecity);
        mphone = findViewById(R.id.phone);
        memail = findViewById(R.id.semail);
        mpassword = findViewById(R.id.spassword);
        mlogint = findViewById(R.id.logint);
        msignUp = findViewById(R.id.signupButton);


        msignUp.setOnClickListener(view -> {
            String fullName = mfullName.getText().toString();
            String homeCity = mhomeCity.getText().toString();
            String phone = mphone.getText().toString();
            String email = memail.getText().toString();
            String password = mpassword.getText().toString();

            // Perform validation on the fields if needed

            // Register the user with email and password through Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Registration successful, save additional user information to Realtime Database

                            String userId = auth.getCurrentUser().getUid();
                            CustomUser user = new CustomUser(fullName, homeCity, phone, email);
                            database.child(userId).setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // Registration and data saving successful
                                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                            // Navigate to the main activity or any other desired destination
                                            startActivity(new Intent(signupActivity.this, MainActivity.class));
                                        } else {
                                            // Error saving user data to Realtime Database
                                            Toast.makeText(this, "Error saving user data to Realtime Database", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Error registering the user with Firebase Authentication
                            Toast.makeText(this, "Error registering the user with Firebase Authentication", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    public void toLogin(View view) {
        startActivity(new Intent(signupActivity.this, MainActivity.class));
    }

}