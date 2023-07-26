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

public class signupActivity extends AppCompatActivity {

    private EditText mfullName, mhomeCity, mphone, memail, mpassword;
    private TextView mlogint;
    private Button msignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

    }

    public void toLogin(View view) {
        startActivity(new Intent(signupActivity.this, MainActivity.class));
    }

    public void toBooking(View view) {
        startActivity(new Intent(signupActivity.this, BookingActivity.class));
    }
}