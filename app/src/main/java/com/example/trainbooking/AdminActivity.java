package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    private TextView availableSeats;
    private TextView bookedSeats;
    private TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_activity);

        availableSeats = findViewById(R.id.available1);
        bookedSeats = findViewById(R.id.booked1);
        amount = findViewById(R.id.amount1);

        // Get a reference to the "bookings" node in your Firebase Realtime Database
        DatabaseReference bookingsRef = FirebaseDatabase.getInstance().getReference().child("Trip 1 Travelers 06:00 AM");

        // Get the total number of seats available
        final int totalSeats = 26;

        // Set a ValueEventListener to listen for changes in the "bookings" node
        bookingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get the number of bookings (children) in the "bookings" node
                long bookingsCount = dataSnapshot.getChildrenCount();

                // Calculate the number of seats booked and available
                int seatsBooked = (int) bookingsCount;
                int seatsAvailable = totalSeats - seatsBooked;

                // Calculate the total sum of money paid by all travelers
                double totalSum = 0.0;
                for (DataSnapshot bookingSnapshot : dataSnapshot.getChildren()) {
                    Double amountPaid = bookingSnapshot.child("amount").getValue(Double.class);
                    if (amountPaid != null) {
                        totalSum += amountPaid;
                    }
                }

                // Update the TextViews with the seat information
                bookedSeats.setText(String.valueOf("Booked Seats: " + seatsBooked));
                availableSeats.setText(String.valueOf("Available Seats: " + seatsAvailable));
                amount.setText("Ksh. " + totalSum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur
            }
        });

        
    }
}