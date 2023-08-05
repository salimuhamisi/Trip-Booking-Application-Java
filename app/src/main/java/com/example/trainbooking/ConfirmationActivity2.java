package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ConfirmationActivity2 extends AppCompatActivity {
    private DatabaseReference database;

    private EditText namec, mpesac, cvv, account;
    private TextView confirmAmount, confirmDate, confirmTrip, seatnumberc;
    private ImageView mpesa, visa;
    private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        mpesa = findViewById(R.id.mpesa);
        visa = findViewById(R.id.visa);
        cvv = findViewById(R.id.cvv);
        account = findViewById(R.id.account);
        pay = findViewById(R.id.payc);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpesac.getVisibility() == View.VISIBLE) {
                    // Hide the EditText
                    mpesac.setVisibility(View.GONE);
                } else {
                    // Show the EditText
                    mpesac.setVisibility(View.VISIBLE);
                }
            }
        });

        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getVisibility() == View.VISIBLE) {
                    // Hide the EditTexts
                    account.setVisibility(View.GONE);
                    cvv.setVisibility(View.GONE); // Assuming visac is the ID of the second EditText
                } else {
                    // Show the EditTexts
                    cvv.setVisibility(View.VISIBLE);
                    account.setVisibility(View.VISIBLE);
                }
            }
        });

        // Retrieve the current user's ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // Get a reference to the Firebase Realtime Database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);

        // Add a ValueEventListener to retrieve the user's data from the database
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the user's data
                String name = dataSnapshot.child("fullName").getValue(String.class);
                String phoneNumber = dataSnapshot.child("phone").getValue(String.class);

                // Set the retrieved data in the EditText fields
                namec.setText(name);
                mpesac.setText(phoneNumber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error if the data retrieval fails
            }
        });

//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        database = FirebaseDatabase.getInstance().getReference("Trip 2 Travelers 1:00 PM");

        namec = findViewById(R.id.namec);
        mpesac = findViewById(R.id.mpesac);
        confirmAmount = findViewById(R.id.amountc);
        confirmDate = findViewById(R.id.datec);
        confirmTrip = findViewById(R.id.tripc);
        seatnumberc = findViewById(R.id.seatnumberc);
        pay = findViewById(R.id.payc);


        // Receive the price value from the previous activity

        Intent rDetails = getIntent();
        String newAmount = rDetails.getStringExtra("cAmount");
        String newfromto = rDetails.getStringExtra("cTrip");
        String newtraveldates = rDetails.getStringExtra("cDate");
        String newSeat = rDetails.getStringExtra("seatN");


        //Display

        confirmTrip.setText(newfromto);
        confirmDate.setText(newtraveldates);
        confirmAmount.setText(newAmount);
        seatnumberc.setText(newSeat);



    }

    private void showDialog() {

        String newAmount = confirmAmount.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm pay Ksh. " + newAmount + " to KENYA RAILWAYS COMPANY")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        processPayment(pay); // Pass the 'pay' button view as the parameter
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


    public void processPayment(View view) {


        //get the details
        String cTrip = confirmTrip.getText().toString();
        String cDate = confirmDate.getText().toString();
        String cAmount = confirmAmount.getText().toString();
        String seatNo = seatnumberc.getText().toString();
        String cName = namec.getText().toString().trim();
        String cmpesa = mpesac.getText().toString().trim();

        // Convert the newAmount to a double and store it in the traveler object
        double amount = Double.parseDouble(cAmount);
        // Save the Traveler's information to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Trip 2 Travelers 1:00 PM");
        String travelerId = databaseRef.push().getKey(); // Generate a unique key for the traveler
        Traveler traveler = new Traveler(cTrip, cDate, amount, seatNo, cName, cmpesa);

        traveler.setAmount(amount);

        databaseRef.child(travelerId).setValue(traveler)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Traveler information saved successfully
                        Toast.makeText(ConfirmationActivity2.this, "Booking was Successful.", Toast.LENGTH_SHORT).show();

                        // TODO: Update the seat status in the UI and disable the selected seat
                        // You can do this by changing the seat color and setting a flag to indicate that the seat is occupied.
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save traveler information
                        Toast.makeText(ConfirmationActivity2.this, "Booking Failed.", Toast.LENGTH_SHORT).show();
                    }
                });



        //send the details to ticketlayout Activity
        Intent sDetails = new Intent(ConfirmationActivity2.this, TicketLayoutActivity.class);
        sDetails.putExtra("cTrip", cTrip); // Pass the price value to the next activity
        sDetails.putExtra("cDate", cDate); // Pass the tripSegment string to the next activity
        sDetails.putExtra("cAmount", cAmount); // Pass the travel dates string to the next activity
        sDetails.putExtra("seatNo", seatNo); // Pass the travel dates string to the next activity
        sDetails.putExtra("cName", cName); // Pass the travel dates string to the next activity
        sDetails.putExtra("cmpesa", cmpesa); // Pass the travel dates string to the next activity
        startActivity(sDetails);
    }
}