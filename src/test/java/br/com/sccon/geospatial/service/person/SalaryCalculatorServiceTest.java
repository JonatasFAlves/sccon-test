package br.com.sccon.geospatial.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaryCalculatorServiceTest {

    private SalaryCalculatorService salaryCalculatorService;

    @BeforeEach
    void setUp() {
        salaryCalculatorService = new SalaryCalculatorService();
    }

    @ParameterizedTest
    @CsvSource({ "0, 1558.00", "1, 2338.44", "2, 3259.36", "3, 4346.04" })
    void shouldCalculateSalaryFromAdmissionDate(Long yearsWorkingInCompany, BigDecimal expectedSalary) {
        LocalDate admissionDate = LocalDate.now().minusYears(yearsWorkingInCompany);
        BigDecimal salary = salaryCalculatorService.getSalaryFromAdmissionDate(admissionDate);
        assertEquals(expectedSalary, salary);
    }
}