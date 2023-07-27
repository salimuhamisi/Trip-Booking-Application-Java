package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView forgotPassword;
    private Button loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.loginemail);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        // Validate user input
        if (TextUtils.isEmpty(email)) {
            loginEmail.setError("Please enter your email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            loginPassword.setError("Please enter your password");
            return;
        }
        // Perform login with Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            // Navigate to the BookingActivity
                            startActivity(new Intent(MainActivity.this, BookingActivity.class));
                            finish();
                        } else {
                            // Login failed
                            Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void toSignup(View view) {
        startActivity(new Intent(MainActivity.this, signupActivity.class));
    }
}