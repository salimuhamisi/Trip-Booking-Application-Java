package com.example.trainbooking;

public class Traveler {
    private String trip;
    private String date;
    private String amount;
    private String seatNumber;
    private String name;
    private String mpesa;

    public Traveler() {
        // Default constructor required for Firebase Realtime Database
    }

    public Traveler(String trip, String date, String amount, String seatNumber, String name, String mpesa) {
        this.trip = trip;
        this.date = date;
        this.amount = amount;
        this.seatNumber = seatNumber;
        this.name = name;
        this.mpesa = mpesa;
    }

    // Getters
    public String getTrip() {
        return trip;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getName() {
        return name;
    }

    public String getMpesa() {
        return mpesa;
    }

    // Setters
    public void setTrip(String trip) {
        this.trip = trip;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMpesa(String mpesa) {
        this.mpesa = mpesa;
    }
}

