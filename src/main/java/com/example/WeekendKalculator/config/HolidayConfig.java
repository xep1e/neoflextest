package com.example.WeekendKalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.Set;

@Configuration
public class HolidayConfig {

    @Bean
    public Set<LocalDate> russianHolidays() {
        return Set.of(
                // Январь
                LocalDate.of(2024, 1, 1),  // Новый год
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 4),
                LocalDate.of(2024, 1, 5),
                LocalDate.of(2024, 1, 6),
                LocalDate.of(2024, 1, 7),  // Рождество
                LocalDate.of(2024, 1, 8),

                // Февраль
                LocalDate.of(2024, 2, 23), // День защитника Отечества

                // Март
                LocalDate.of(2024, 3, 8),  // Международный женский день

                // Май
                LocalDate.of(2024, 5, 1),  // Праздник Весны и Труда
                LocalDate.of(2024, 5, 9),  // День Победы

                // Июнь
                LocalDate.of(2024, 6, 12), // День России

                // Ноябрь
                LocalDate.of(2024, 11, 4)  // День народного единства
        );
    }
}