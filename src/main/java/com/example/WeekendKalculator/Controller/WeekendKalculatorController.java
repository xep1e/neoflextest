package com.example.WeekendKalculator.Controller;

import com.example.WeekendKalculator.exceptions.InvalidDateException;
import com.example.WeekendKalculator.exceptions.InvalidNumberException;
import com.example.WeekendKalculator.services.WeekendKalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WeekendKalculatorController {
    private final WeekendKalculatorService service;

    public WeekendKalculatorController(WeekendKalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculate(
            @RequestParam String averageSalary,
            @RequestParam String vacationDays,
            @RequestParam(required = false) String startDate) {

        try {
            return ResponseEntity.ok(service.calculateFromStrings(
                    averageSalary,
                    vacationDays,
                    startDate
            ));
        } catch (InvalidNumberException | InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}