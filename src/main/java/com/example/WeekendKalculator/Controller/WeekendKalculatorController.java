package com.example.WeekendKalculator.Controller;

import com.example.WeekendKalculator.model.WeekendRequest;

import com.example.WeekendKalculator.services.WeekendKalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@RestController
@RequestMapping("/api")
public class WeekendKalculatorController {
    private final WeekendKalculatorService service;

    public WeekendKalculatorController(WeekendKalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculate(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) String startDate) {

        return ResponseEntity.ok(service.calculate(
                averageSalary,
                vacationDays,
                startDate
        ));
    }
}