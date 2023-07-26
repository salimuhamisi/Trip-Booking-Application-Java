package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {

    private String[] city_from = {"kisumu", "Nakuru", "Nairobi", "Mombasa"};
    private String[] city_to = {"kisumu", "Nakuru", "Nairobi", "Mombasa"};

    private TextView displayDate;
    private TextView DatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        DatePicker = findViewById(R.id.calendar);
        displayDate = (TextView) findViewById(R.id.travelDate);

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.from);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, city_from);
        autoCompleteTextView1.setAdapter(adapter1);

        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.towards);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, city_to);
        autoCompleteTextView2.setAdapter(adapter2);

        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
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

    public void toTrips(View view) {
        startActivity(new Intent(BookingActivity.this, AvailabletripsActivity.class));
    }
}