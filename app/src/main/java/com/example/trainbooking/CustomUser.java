package com.example.trainbooking;

public class CustomUser {
    private String fullName;
    private String homeCity;
    private String phone;
    private String email;

    // Empty constructor (required for Firebase)
    public CustomUser() {
    }

    // Constructor with parameters
    public CustomUser(String fullName, String homeCity, String phone, String email) {
        this.fullName = fullName;
        this.homeCity = homeCity;
        this.phone = phone;
        this.email = email;
    }

    // Getter methods
    public String getFullName() {
        return fullName;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setter methods (if needed)
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
