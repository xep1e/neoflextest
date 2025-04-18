package com.example.WeekendKalculator.ServisesTest;

import com.example.WeekendKalculator.exceptions.*;
import com.example.WeekendKalculator.config.HolidayConfig;
import com.example.WeekendKalculator.services.WeekendKalculatorService;
import com.example.WeekendKalculator.model.WeekendRequest;
import com.example.WeekendKalculator.model.WeekendResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeekendKalculatorServiceTest {

    @Autowired
    private HolidayConfig holidayConfig;


    @Autowired
    private WeekendKalculatorService service;

    //Проверка корректного счета без дат
    @Test
    void calculatenCorrectPayment() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(14);

        WeekendResponse response = service.calculate(request);

        assertEquals(47781.57, response.getPayment(), 0.01);
    }
    //минимальная сум
    @Test
    void calculate_MinimumSalary() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(1);
        request.setVacationDays(1);
        WeekendResponse response = service.calculate(request);
        assertEquals(0.03, response.getPayment(), 0.001);
    }
    //Максильные отпускные
    @Test
    void calculate_MaximumDays() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(365);
        WeekendResponse response = service.calculate(request);
        assertTrue(response.getPayment() > 0);
    }
    //Счет с датами
    @Test
    void calculate_withWeekends() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(7);
        request.setStartDate(LocalDate.of(2024, 4, 6));// 6 апреля суббота

        WeekendResponse response = service.calculate(request);

        assertEquals(17_064.85, response.getPayment(), 0.01);
    }
    //переход на новый год
    @Test
    void calculate_CrossYearTransition() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(10);
        request.setStartDate(LocalDate.of(2024, 12, 28));

        WeekendResponse response = service.calculate(request);

        assertTrue(response.getPayment() > 0);
    }
    //Счет с праздниками
    @Test
    void calculate_withHoliday() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(3);
        request.setStartDate(LocalDate.of(2025, 1, 8));

        WeekendResponse response = service.calculate(request);


        double expectedPayment = 100_000 / 29.3 * 2; // ≈ 6825.94
        assertEquals(expectedPayment, response.getPayment(), 0.01);


    }

    //  Нулевые дни отпуска
    @Test
    void calculate_zeroDays_Exception() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(0);

        assertThrows(ZeroDaysException.class, () -> service.calculate(request));
    }

    // Отрицательная зарплата
    @Test
    void calculate_negativeSalary_Exception() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(-10000);
        request.setVacationDays(14);

        assertThrows(IllegalArgumentException.class, () -> service.calculate(request));
    }
    //все дни выходные
    @Test
    void calculate_allDaysNonWorking_Exception() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(2);
        request.setStartDate(LocalDate.of(2024, 1, 6)); // Суббота и воскресенье

        assertThrows(ZeroDaysException.class, () -> service.calculate(request));
    }
// округление
    @Test
    void calculate_shouldRoundToKopecks() {
        WeekendRequest request = new WeekendRequest();
        request.setAverageSalary(100000);
        request.setVacationDays(1);

        WeekendResponse response = service.calculate(request);

        // 100000 / 29.3 = 3412.96928327645 → должно округлиться до 3412.97
        assertEquals(3412.97, response.getPayment(), 0.001);
    }
}
