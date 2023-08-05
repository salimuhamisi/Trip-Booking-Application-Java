package com.example.trainbooking;

public class Seat {
    private int seatNumber;
    private boolean isSeatOccupied;

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isSeatOccupied = false;
    }

    // Getters and setters
    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isSeatOccupied() {
        return isSeatOccupied;
    }

    public void setSeatOccupied(boolean isSeatOccupied) {
        this.isSeatOccupied = isSeatOccupied;
    }
}