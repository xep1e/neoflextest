package com.example.WeekendKalculator.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("Ошибка валидации: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidDateException(InvalidDateException ex) {
        return ResponseEntity.badRequest().body("Ошибка даты: " + ex.getMessage());
    }

    @ExceptionHandler(ZeroDaysException.class)
    public ResponseEntity<String> handleZeroDaysException(ZeroDaysException ex) {
        return ResponseEntity.badRequest().body("Ошибка дней отпуска: " + ex.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(ArithmeticException ex) {
        return ResponseEntity.badRequest().body("Математическая ошибка: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.internalServerError().body("Внутренняя ошибка: " + ex.getMessage());
    }
}

