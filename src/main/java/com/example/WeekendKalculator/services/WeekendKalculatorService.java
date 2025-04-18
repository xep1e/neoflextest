package com.example.WeekendKalculator.services;

import com.example.WeekendKalculator.config.HolidayConfig;
import com.example.WeekendKalculator.exceptions.InvalidDateException;
import com.example.WeekendKalculator.exceptions.ZeroDaysException;
import com.example.WeekendKalculator.model.WeekendRequest;
import com.example.WeekendKalculator.model.WeekendResponse;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

@Service
public class WeekendKalculatorService {

    private static final double AVG_DAYS_IN_MONTH = 29.3;
    private final Set<LocalDate> holidays;

    public WeekendKalculatorService(HolidayConfig holidayConfig) {
        this.holidays = holidayConfig.russianHolidays();
    }
    public WeekendResponse calculate(double averageSalary, int vacationDays, String startDateStr) {
        WeekendRequest request = new WeekendRequest();

        request.setAverageSalary(averageSalary);
        request.setVacationDays(vacationDays);

        if (startDateStr != null) {
            try {
                request.setStartDate(LocalDate.parse(startDateStr));
            } catch (DateTimeParseException e) {
                throw new InvalidDateException("Неверный формат даты. Используйте YYYY-MM-DD");
            }
        }

        return calculate(request);
    }
    public WeekendResponse calculate(WeekendRequest request) {
        validateRequest(request);
        validateDate(request.getStartDate());

        double dailySalary = calculateDailySalary(request.getAverageSalary());
        int workingDays = calculateWorkingDays(request);

        if (workingDays == 0) {
            throw new ZeroDaysException("Нет рабочих дней для расчета");
        }

        double payment = calculatePayment(dailySalary, workingDays);
        return new WeekendResponse(payment);
    }

    private void validateRequest(WeekendRequest request) {
        if (request.getAverageSalary() <= 0) {
            throw new IllegalArgumentException("Зарплата должна быть положительной");
        }
        if (request.getVacationDays() <= 0) {
            throw new ZeroDaysException("Количество дней отпуска должно быть положительным");
        }
    }

    private void validateDate(LocalDate date) {
        if (date != null && date.toString().length() != 10) {
            throw new InvalidDateException("Неверный формат даты. Используйте YYYY-MM-DD");
        }
    }

    private double calculateDailySalary(double averageSalary) {

        return averageSalary / AVG_DAYS_IN_MONTH;
    }

    private int calculateWorkingDays(WeekendRequest request) {
        return request.getStartDate() != null
                ? countWorkingDays(request.getStartDate(), request.getVacationDays())
                : request.getVacationDays();
    }

    private double calculatePayment(double dailySalary, int workingDays) {
        return Math.round(dailySalary * workingDays * 100) / 100.0;
    }

    private int countWorkingDays(LocalDate startDate, int days) {
        int count = 0;
        LocalDate date = startDate;

        for (int i = 0; i < days; i++) {
            if (!isWeekend(date) && !holidays.contains(date)) {
                count++;
            }
            date = date.plusDays(1);
        }
        return count;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}