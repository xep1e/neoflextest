package com.example.WeekendKalculator.exceptions;


public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }
}
