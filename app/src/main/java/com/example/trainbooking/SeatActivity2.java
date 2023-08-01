package com.example.trainbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity2 extends AppCompatActivity {

    private int seatCount = 0;
    private TextView SelectedSeatNumber, trip1, date1, amount;
    private List<Integer> SeatNumbers = new ArrayList<>();

    private List<Seat> availableSeats = new ArrayList<>();
    private Button lastSelectedSeat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat1);

        // Assuming the total number of seats in your train is 26
        int totalSeats = 26;
        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = new Seat(i);
            availableSeats.add(seat);
        }

        SelectedSeatNumber = findViewById(R.id.seatnumber);
        trip1 = findViewById(R.id.trip1);
        date1 = findViewById(R.id.date1);
        amount = findViewById(R.id.amount);


        // Receive the price value from the previous activity
        Intent rDetails = getIntent();
        String newAmount = rDetails.getStringExtra("newAmount");
        String newfromto = rDetails.getStringExtra("newfromto");
        String newtraveldates = rDetails.getStringExtra("newtraveldates");
        String newtimev = rDetails.getStringExtra("newtimev");

        //Display

        trip1.setText(newfromto);
        date1.setText(newtraveldates + "   "+ newtimev);
        //amount.setText(String.valueOf(newAmount));
        amount.setText(newAmount);


    }




    // Updated onChairClick method
    public void onChairClick(View view) {
        Button button = (Button) view;
        int seatNumber = Integer.parseInt(button.getText().toString());

        Seat selectedSeat = getSeatByNumber(seatNumber); // Get the corresponding Seat object

        if (selectedSeat == null) {
            // Handle the case where the Seat object is not found
            return;
        }

        // Check if the seat is booked (inactive)
        if (selectedSeat.isSeatOccupied()) {
            // Seat is already booked and inactive
            // You can show a message to the user or handle it in any other way you prefer
            return;
        }

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
            button.setBackgroundColor(Color.YELLOW); // Change to yellow for selected seat
            button.setTag(1);
            seatCount = 1; // Set the seat count to 1 since only one seat is selected at a time
            SeatNumbers.clear();
            SeatNumbers.add(seatNumber);
            lastSelectedSeat = button; // Store the reference to the last selected seat
        }

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


    private Seat getSeatByNumber(int seatNumber) {
        // Assuming you have a list of available seats named "availableSeats"
        for (Seat seat : availableSeats) {
            if (seat.getSeatNumber() == seatNumber) {
                return seat;
            }
        }
        return null; // Return null if the seat number is not found
    }


    public void confirmDetails(View view) {

        String cTrip = trip1.getText().toString();
        String cDate = date1.getText().toString();
        String cAmount = amount.getText().toString();
        String seatN = SelectedSeatNumber.getText().toString();


        // Create an Intent to start the next activity
        Intent sDetails = new Intent(SeatActivity2.this, ConfirmationActivity2.class);
        sDetails.putExtra("cTrip", cTrip); // Pass the price value to the next activity
        sDetails.putExtra("cDate", cDate); // Pass the tripSegment string to the next activity
        sDetails.putExtra("cAmount", cAmount); // Pass the travel dates string to the next activity
        sDetails.putExtra("seatN", seatN); // Pass the travel dates string to the next activity
        startActivity(sDetails);

        //startActivity(new Intent(SeatActivity1.this, ConfirmationActivity.class));
    }
}