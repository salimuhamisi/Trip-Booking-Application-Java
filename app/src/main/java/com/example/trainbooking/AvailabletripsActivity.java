package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class AvailabletripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabletrips);
    }

    public void seats1(View view) {
        startActivity(new Intent(AvailabletripsActivity.this, SeatActivity1.class));
    }

    public void seats2(View view) {
        startActivity(new Intent(AvailabletripsActivity.this, SeatActivity2.class));
    }
    public void seats3(View view) {
        startActivity(new Intent(AvailabletripsActivity.this, SeatActivity3.class));
    }
}