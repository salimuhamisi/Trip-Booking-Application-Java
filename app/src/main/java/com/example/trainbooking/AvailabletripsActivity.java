package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AvailabletripsActivity extends AppCompatActivity {

    private TextView amount1, amount2, amount3, fromto, travelDates, fullNames, timev, amountv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabletrips);

        amount1 = findViewById(R.id.amount1);
        amount2 = findViewById(R.id.amount2);
        amount3 = findViewById(R.id.amount3);
        fromto = findViewById(R.id.fromto);
        travelDates = findViewById(R.id.dates);
        fullNames = findViewById(R.id.name);
        timev = findViewById(R.id.timev);
        amountv = findViewById(R.id.amountv);

        // Receive the price value from the previous activity
        Intent intent = getIntent();
        double price = intent.getDoubleExtra("price", 0.0);
        String tripSegment = intent.getStringExtra("tripSegment");
        String Dates = intent.getStringExtra("travelDates");

        // Display the price in the TextView
        amount1.setText("Ksh." + price);
        amount2.setText("Ksh." + price);
        amount3.setText("Ksh." + price);
        fromto.setText(tripSegment);
        travelDates.setText(Dates);
        amountv.setText(String.valueOf(price)); // Set the converted String as the text for the TextView
        //String priceString = String.valueOf(price); // Convert the double to a String

    }

    public void seats1(View view) {

        // get these strings to pass them to next activity
        timev.setText("06:00 AM");
        String newAmount = amountv.getText().toString();
        String newfromto = fromto.getText().toString();
        String newtraveldates = travelDates.getText().toString();
        String newtimev = timev.getText().toString();



        // Create an Intent to start the next activity
        Intent sDetails = new Intent(AvailabletripsActivity.this, SeatActivity1.class);
        sDetails.putExtra("newAmount", newAmount); // Pass the price value to the next activity
        sDetails.putExtra("newfromto", newfromto); // Pass the tripSegment string to the next activity
        sDetails.putExtra("newtraveldates", newtraveldates); // Pass the travel dates string to the next activity
        sDetails.putExtra("newtimev", newtimev); // Pass the travel dates string to the next activity
        startActivity(sDetails);
    }

    public void seats2(View view) {
        timev.setText("1:00 PM");
        String newAmount = amountv.getText().toString();
        String newfromto = fromto.getText().toString();
        String newtraveldates = travelDates.getText().toString();
        String newtimev = timev.getText().toString();



        // Create an Intent to start the next activity
        Intent sDetails = new Intent(AvailabletripsActivity.this, SeatActivity2.class);
        sDetails.putExtra("newAmount", newAmount); // Pass the price value to the next activity
        sDetails.putExtra("newfromto", newfromto); // Pass the tripSegment string to the next activity
        sDetails.putExtra("newtraveldates", newtraveldates); // Pass the travel dates string to the next activity
        sDetails.putExtra("newtimev", newtimev); // Pass the travel dates string to the next activity
        startActivity(sDetails);
    }
    public void seats3(View view) {
        timev.setText("6:00 PM");
        String newAmount = amountv.getText().toString();
        String newfromto = fromto.getText().toString();
        String newtraveldates = travelDates.getText().toString();
        String newtimev = timev.getText().toString();



        // Create an Intent to start the next activity
        Intent sDetails = new Intent(AvailabletripsActivity.this, SeatActivity3.class);
        sDetails.putExtra("newAmount", newAmount); // Pass the price value to the next activity
        sDetails.putExtra("newfromto", newfromto); // Pass the tripSegment string to the next activity
        sDetails.putExtra("newtraveldates", newtraveldates); // Pass the travel dates string to the next activity
        sDetails.putExtra("newtimev", newtimev); // Pass the travel dates string to the next activity
        startActivity(sDetails);
    }
}