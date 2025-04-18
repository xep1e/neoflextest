package com.example.WeekendKalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class HolidayConfig {

    @Bean
    public Set<LocalDate> russianHolidays() {
        Set<LocalDate> holidays = new HashSet<>();
        int currentYear = LocalDate.now().getYear();

        // Добавляем праздники на текущий и следующий год
        for (int year = currentYear; year <= currentYear + 1; year++) {
            holidays.addAll(Set.of(
                    // Январь
                    LocalDate.of(year, 1, 1),  // Новый год
                    LocalDate.of(year, 1, 2),
                    LocalDate.of(year, 1, 3),
                    LocalDate.of(year, 1, 4),
                    LocalDate.of(year, 1, 5),
                    LocalDate.of(year, 1, 6),
                    LocalDate.of(year, 1, 7),  // Рождество
                    LocalDate.of(year, 1, 8),

                    // Февраль
                    LocalDate.of(year, 2, 23), // День защитника Отечества

                    // Март
                    LocalDate.of(year, 3, 8),  // Международный женский день

                    // Май
                    LocalDate.of(year, 5, 1),  // Праздник Весны и Труда
                    LocalDate.of(year, 5, 9),  // День Победы

                    // Июнь
                    LocalDate.of(year, 6, 12), // День России

                    // Ноябрь
                    LocalDate.of(year, 11, 4)  // День народного единства
            ));
        }

        return holidays;
    }
}