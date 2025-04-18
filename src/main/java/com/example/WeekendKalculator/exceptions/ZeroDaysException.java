package com.example.WeekendKalculator.exceptions;

public class ZeroDaysException extends RuntimeException {
    public ZeroDaysException(String message) {
        super(message);
    }
}
