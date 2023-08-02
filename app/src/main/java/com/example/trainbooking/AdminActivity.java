package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {

    private TextView availableSeats1,bookedSeats1,amount1 ;
    private TextView availableSeats2,bookedSeats2, amount2;
    private TextView availableSeats3,bookedSeats3, amount3;

    // Get a reference to the "bookings" node in your Firebase Realtime Database
    DatabaseReference bookingsRef2 = FirebaseDatabase.getInstance().getReference().child("Trip 2 Travelers 1:00 PM");
    DatabaseReference bookingsRef3 = FirebaseDatabase.getInstance().getReference().child("Trip 3 Travelers 6:00 PM");
    DatabaseReference bookingsRef1 = FirebaseDatabase.getInstance().getReference().child("Trip 1 Travelers 06:00 AM");
    // Get the total number of seats available
    final int totalSeats = 26;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_activity);

        availableSeats1 = findViewById(R.id.available1);
        bookedSeats1 = findViewById(R.id.booked1);
        amount1 = findViewById(R.id.amount1);

        availableSeats2 = findViewById(R.id.available2);
        bookedSeats2 = findViewById(R.id.booked2);
        amount2 = findViewById(R.id.amount2);

        availableSeats3 = findViewById(R.id.available3);
        bookedSeats3 = findViewById(R.id.booked3);
        amount3 = findViewById(R.id.amount3);

    }

    public void layout1(View view) {

        // Set a ValueEventListener to listen for changes in the "bookings" nodes
        ValueEventListener valueEventListener1 = new ValueEventListener()  {
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
                bookedSeats1.setText("Booked Seats: " + seatsBooked);
                availableSeats1.setText(String.valueOf("Available Seats: " + seatsAvailable));
                amount1.setText("Ksh. " + totalSum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur
                Toast.makeText(AdminActivity.this, "DatabaseError databaseError", Toast.LENGTH_SHORT).show();
            }
        };
        bookingsRef1.addValueEventListener(valueEventListener1);
    }

    public void layout2(View view) {
        // Set a ValueEventListener to listen for changes in the "bookings" nodes
        ValueEventListener valueEventListener2 = new ValueEventListener()  {
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
                bookedSeats2.setText("Booked Seats: " + seatsBooked);
                availableSeats2.setText(String.valueOf("Available Seats: " + seatsAvailable));
                amount2.setText("Ksh. " + totalSum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur
                Toast.makeText(AdminActivity.this, "DatabaseError databaseError", Toast.LENGTH_SHORT).show();
            }
        };
        bookingsRef2.addValueEventListener(valueEventListener2);
    }

    public void layout3(View view) {
        // Set a ValueEventListener to listen for changes in the "bookings" nodes
        ValueEventListener valueEventListener3 = new ValueEventListener()  {
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
                bookedSeats3.setText("Booked Seats: " + seatsBooked);
                availableSeats3.setText(String.valueOf("Available Seats: " + seatsAvailable));
                amount3.setText("Ksh. " + totalSum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that may occur
                Toast.makeText(AdminActivity.this, "DatabaseError databaseError", Toast.LENGTH_SHORT).show();
            }
        };
        bookingsRef3.addValueEventListener(valueEventListener3);
    }
}