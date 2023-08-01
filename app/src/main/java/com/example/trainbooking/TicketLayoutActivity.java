package com.example.trainbooking;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
public class TicketLayoutActivity extends AppCompatActivity {
    private static final String TICKET_FILE_NAME = "ticket.pdf";
    private TextView tName, ttrip, tdate, tamount, tseatnumber, tphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_layout);

        tName = findViewById(R.id.tName);
        ttrip = findViewById(R.id.ttrip);
        tdate = findViewById(R.id.tdate);
        tamount = findViewById(R.id.tamount);
        tseatnumber = findViewById(R.id.tseatnumber);
        tphone = findViewById(R.id.tphone);


        //Receive details from Confirm activity
        Intent rDetails = getIntent();
        String lname = rDetails.getStringExtra("cName");
        String ltrip = rDetails.getStringExtra("cTrip");
        String ldate = rDetails.getStringExtra("cDate");
        String lamount = rDetails.getStringExtra("cAmount");
        String lseatNumber = rDetails.getStringExtra("seatNo");
        String lphone = rDetails.getStringExtra("cmpesa");

        //Display them;
        tName.setText(lname);
        ttrip.setText(ltrip);
        tdate.setText(ldate);
        tamount.setText(lamount);
        tseatnumber.setText(lseatNumber);
        tphone.setText(lphone);

        // Find the "Generate Ticket" button by its ID and set the click listener
        findViewById(R.id.getTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGenerateAndSaveTicketClick();
            }
        });
    }

    // Generate the ticket PDF
    private void generateTicketPDF() {
        // Retrieve the information from the TextViews
        String name = tName.getText().toString();
        String trip = ttrip.getText().toString();
        String date = tdate.getText().toString();
        String amount = tamount.getText().toString();
        String seatNumber = tseatnumber.getText().toString();
        String phone = tphone.getText().toString();

        // Create the PDF document
        Document document = new Document();
        try {
            // Save the PDF to external storage
            File ticketFile = new File(getExternalFilesDir(null), TICKET_FILE_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(ticketFile);
            PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            // Add ticket information as paragraphs to the PDF document
            document.add(new Paragraph("KENYA RAILWAYS SERVICES"));
            document.add(new Paragraph("Name:    " + name));
            document.add(new Paragraph("Trip:    " + trip));
            document.add(new Paragraph("Date:    " + date));
            document.add(new Paragraph("Amount Paid:   " + amount));
            document.add(new Paragraph("Seat Number:    " + seatNumber));
            document.add(new Paragraph("Phone Number:    " + phone));

            document.close();

            // Notify user that the ticket has been saved
            Toast.makeText(this, "Ticket saved successfully", Toast.LENGTH_SHORT).show();
            // Allow the user to share the PDF
            sharePdf(ticketFile);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            // Notify user if an error occurred while saving the ticket
            Toast.makeText(this, "Failed to save the ticket", Toast.LENGTH_SHORT).show();
        }
    }

    private void sharePdf(File pdfFile) {
        Uri pdfUri = Uri.fromFile(pdfFile);

        // Allow the user to share the PDF using an Intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        startActivity(Intent.createChooser(shareIntent, "Share Ticket"));
    }

    // Handle "Generate Ticket" button click
    private void onGenerateAndSaveTicketClick() {
        // Generate the ticket PDF
        generateTicketPDF();
    }
}