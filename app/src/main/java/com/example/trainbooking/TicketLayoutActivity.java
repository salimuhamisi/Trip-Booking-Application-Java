package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class TicketLayoutActivity extends AppCompatActivity {

    private TextView tName, ttrip, tdate, tamount, tseatnumber, tphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_layout);

        // Inflate the ticket_layout.xml and obtain the View object
        View ticketLayout = LayoutInflater.from(this).inflate(R.layout.activity_ticket_layout, null);

        tName = findViewById(R.id.tName);
        ttrip = findViewById(R.id.ttrip);
        tdate = findViewById(R.id.tdate);
        tamount = findViewById(R.id.tamount);
        tseatnumber = findViewById(R.id.tseatnumber);
        tphone = findViewById(R.id.tphone);


        // Receive the price value from the previous activity

        Intent rDetails = getIntent();
        String newAmount = rDetails.getStringExtra("cAmount");
        String newfromto = rDetails.getStringExtra("cTrip");
        String newtraveldates = rDetails.getStringExtra("cDate");
        String seatNo = rDetails.getStringExtra("seatNo");
        String Name = rDetails.getStringExtra("cName");
        String mpesa = rDetails.getStringExtra("cmpesa");


        //Display
        tamount.setText(newAmount);
        ttrip.setText(newfromto);
        tdate.setText(newtraveldates);
        tseatnumber.setText(seatNo);
        tName.setText(Name);
        tphone.setText(mpesa);


    }
}