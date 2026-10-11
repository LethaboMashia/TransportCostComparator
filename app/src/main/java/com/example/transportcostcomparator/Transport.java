package com.example.transportcostcomparator;

public class Transport {

    private String mode;
    private double distancePerDay;
    private double costPerKm;
    private int travelDaysPerMonth;
    private String transportType;

    public Transport(String mode, double distancePerDay, double costPerKm,
                     int travelDaysPerMonth, String transportType) {
        this.mode = mode;
        this.distancePerDay = distancePerDay;
        this.costPerKm = costPerKm;
        this.travelDaysPerMonth = travelDaysPerMonth;
        this.transportType = transportType;
    }

    public String getMode() {
        return mode;
    }

    public double getDistancePerDay() {
        return distancePerDay;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

    public int getTravelDaysPerMonth() {
        return travelDaysPerMonth;
    }

    public String getTransportType() {
        return transportType;
    }
}
