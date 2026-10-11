package com.example.transportcostcomparator;

public class TransportCalculator {

    public double calculateDailyCost(Transport transport) {
        return transport.getDistancePerDay() * transport.getCostPerKm();
    }

    public double calculateMonthlyCost(Transport transport) {
        return calculateDailyCost(transport) * transport.getTravelDaysPerMonth();
    }

    public double calculateMonthlyDistance(Transport transport) {
        return transport.getDistancePerDay() * transport.getTravelDaysPerMonth();
    }
}