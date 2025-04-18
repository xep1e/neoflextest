package com.example.WeekendKalculator.model;

public class WeekendResponse {
    private double payment;

    public WeekendResponse(double payment) {
        this.payment = payment;
    }


    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}