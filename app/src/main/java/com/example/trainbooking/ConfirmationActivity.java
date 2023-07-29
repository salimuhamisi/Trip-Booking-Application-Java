package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ConfirmationActivity extends AppCompatActivity {
    private DatabaseReference database;

    private EditText namec, mpesac;
    private TextView confirmAmount, confirmDate, confirmTrip, seatnumberc;
    private Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        database = FirebaseDatabase.getInstance().getReference("Travelers");

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

    public void createPdf(View view) {


        //get the details
        String cTrip = confirmTrip.getText().toString();
        String cDate = confirmDate.getText().toString();
        String cAmount = confirmAmount.getText().toString();
        String seatNo = seatnumberc.getText().toString();
        String cName = namec.getText().toString().trim();
        String cmpesa = mpesac.getText().toString().trim();

        //Take a record to the database
        // Save the Traveler's information to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Travelers");
        String travelerId = databaseRef.push().getKey(); // Generate a unique key for the traveler
        Traveler traveler = new Traveler(cTrip, cDate, cAmount, seatNo, cName, cmpesa);
        databaseRef.child(travelerId).setValue(traveler)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Traveler information saved successfully
                        Toast.makeText(ConfirmationActivity.this, "Booking was Successful.", Toast.LENGTH_SHORT).show();

                        // TODO: Update the seat status in the UI and disable the selected seat
                        // You can do this by changing the seat color and setting a flag to indicate that the seat is occupied.
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save traveler information
                        Toast.makeText(ConfirmationActivity.this, "Booking Failed.", Toast.LENGTH_SHORT).show();
                    }
                });



        //send the details to ticketlayout Activity
        Intent sDetails = new Intent(ConfirmationActivity.this, TicketLayoutActivity.class);
        sDetails.putExtra("cTrip", cTrip); // Pass the price value to the next activity
        sDetails.putExtra("cDate", cDate); // Pass the tripSegment string to the next activity
        sDetails.putExtra("cAmount", cAmount); // Pass the travel dates string to the next activity
        sDetails.putExtra("seatNo", seatNo); // Pass the travel dates string to the next activity
        sDetails.putExtra("cName", cName); // Pass the travel dates string to the next activity
        sDetails.putExtra("cmpesa", cmpesa); // Pass the travel dates string to the next activity
        startActivity(sDetails);
    }
}