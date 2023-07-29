package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private AutoCompleteTextView tFrom, tTo;

    private String[] city_from = {"Kisumu", "Nakuru", "Nairobi", "Mombasa"};
    private String[] city_to = {"Kisumu", "Nakuru", "Nairobi", "Mombasa"};

    private TextView displayDate;
    private TextView DatePicker;

    private TextView viewPrice, travelDates;
    private TripPrices tripPrices;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tripPrices = new TripPrices();

        DatePicker = findViewById(R.id.calendar);
        displayDate = (TextView) findViewById(R.id.travelDate);
        viewPrice = findViewById(R.id.showprice);
        tFrom = findViewById(R.id.from);
        tTo = findViewById(R.id.towards);
        search = findViewById(R.id.search);
        travelDates = findViewById(R.id.travelDate);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTripPrice();
            }
        });


        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, city_from);
        tFrom.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, city_to);
        tTo.setAdapter(adapter2);

        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void calculateTripPrice() {
        String from = tFrom.getText().toString().trim();
        String to = tTo.getText().toString().trim();
        String Dates = travelDates.getText().toString().trim();

        String tripSegment = from + "-" + to;
        double price = tripPrices.getPrice(tripSegment);

        viewPrice.setText("Ksh." + price);



        // Create an Intent to start the next activity
        Intent intent = new Intent(BookingActivity.this, AvailabletripsActivity.class);
        intent.putExtra("price", price); // Pass the price value to the next activity
        intent.putExtra("tripSegment", tripSegment); // Pass the tripSegment string to the next activity
        intent.putExtra("travelDates", Dates); // Pass the travel dates string to the next activity
        startActivity(intent);
    }

    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a date picker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // Update TextView with selected date
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        String formattedDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(selectedDate.getTime());
                        displayDate.setText(formattedDate);
                    }
                }, year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }
}