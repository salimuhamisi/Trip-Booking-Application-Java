package com.example.trainbooking;

import java.util.HashMap;
import java.util.Map;

public class TripPrices {

    private Map<String, Double> prices;

    public TripPrices() {
        prices = new HashMap<>();

        // fixed prices for different town combinations
        prices.put("Kisumu-Mombasa", 2000.0);
        prices.put("Mombasa-Kisumu", 2000.0);
        prices.put("Kisumu-Nairobi", 1500.0);
        prices.put("Nairobi-Kisumu", 1500.0);
        prices.put("Kisumu-Nakuru", 1000.0);
        prices.put("Nakuru-Kisumu", 1000.0);
        prices.put("Nakuru-Mombasa", 1500.0);
        prices.put("Mombasa-Nakuru", 1500.0);
        prices.put("Nakuru-Nairobi", 1000.0);
        prices.put("Nairobi-Nakuru", 1000.0);
        prices.put("Nairobi-Mombasa", 500.0);
        prices.put("Mombasa-Nairobi", 500.0);// working
    }

    public double getPrice(String tripSegment) {
        Double price = prices.get(tripSegment);
        return price != null ? price : 0.0;
    }
}
