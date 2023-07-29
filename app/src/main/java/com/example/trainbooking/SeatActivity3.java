package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity3 extends AppCompatActivity {

    private int seatCount = 0;
    private TextView SeatCount;
    private TextView SelectedSeatNumber;
    private List<Integer> SeatNumbers = new ArrayList<>();
    private Button lastSelectedSeat = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat3);

        SeatCount = findViewById(R.id.selected);
        SelectedSeatNumber = findViewById(R.id.seatnumber);
    }

    public void onChairClick(View view) {
        Button button = (Button) view;
        int seatNumber = Integer.parseInt(button.getText().toString());

        // Check if the seat is already selected
        if (SeatNumbers.contains(seatNumber)) {
            // Deselect the seat
            button.setBackgroundColor(Color.GRAY); // Change to the original color
            button.setTag(0);
            seatCount--;
            SeatNumbers.remove(Integer.valueOf(seatNumber));
        } else {
            // Check if there was a previously selected seat
            if (lastSelectedSeat != null) {
                // Deselect the last selected seat
                lastSelectedSeat.setBackgroundColor(Color.GRAY); // Change to the original color
                lastSelectedSeat.setTag(0);
                SeatNumbers.remove(Integer.valueOf(Integer.parseInt(lastSelectedSeat.getText().toString())));
            }

            // Select the new seat
            button.setBackgroundColor(Color.YELLOW); // Change to the desired new color
            button.setTag(1);
            seatCount = 1; // Set the seat count to 1 since only one seat is selected at a time
            SeatNumbers.clear();
            SeatNumbers.add(seatNumber);
            lastSelectedSeat = button; // Store the reference to the last selected seat
        }

        // Update the seat count TextView
        SeatCount.setText(String.valueOf(seatCount));

        // Update the selected seat number TextView
        String selectedSeatsText = " ";
        for (int i = 0; i < SeatNumbers.size(); i++) {
            selectedSeatsText += String.valueOf(SeatNumbers.get(i));
            if (i < SeatNumbers.size() - 1) {
                selectedSeatsText += ", ";
            }
        }
        SelectedSeatNumber.setText(selectedSeatsText);
    }
}