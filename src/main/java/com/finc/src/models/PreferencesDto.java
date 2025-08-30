package com.finc.src.models;

import java.util.UUID;

public class PreferencesDto {
    private UUID userId;
    private double expenses;
    private double wants;
    private double savings;
    
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public double getExpenses() {
        return expenses;
    }
    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
    public double getWants() {
        return wants;
    }
    public void setWants(double wants) {
        this.wants = wants;
    }
    public double getSavings() {
        return savings;
    }
    public void setSavings(double savings) {
        this.savings = savings;
    }

    
}
