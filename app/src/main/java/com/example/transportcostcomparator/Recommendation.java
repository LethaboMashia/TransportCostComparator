package com.example.transportcostcomparator;

public class Recommendation {

    public String getCategory(double monthlyCost) {
        if (monthlyCost < 1000) return "Low Cost";
        else if (monthlyCost < 2000) return "Moderate";
        else if (monthlyCost < 3000) return "High";
        else if (monthlyCost < 5000) return "Very High";
        else return "Excessive";
    }

    public String getRecommendation(double monthlyCost) {
        String category = getCategory(monthlyCost);
        switch (category) {
            case "Low Cost": return "Continue using your current transport method.";
            case "Moderate": return "Consider carpooling where possible.";
            case "High": return "Reduce unnecessary trips and combine errands.";
            case "Very High": return "Consider public transport for regular commuting.";
            default: return "Immediate action is recommended to reduce transport costs.";
        }
    }
}