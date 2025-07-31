package br.com.sccon.geospatial.service.person;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SalaryCalculatorService {

    private final BigDecimal minWage = new BigDecimal("1302.00");
    private final BigDecimal initialSalary = new BigDecimal("1558.00");

    public BigDecimal getSalary(LocalDate admissionDate, String output) throws Exception {
        if(!StringUtils.hasText(output)){
            throw new IllegalArgumentException("Output cannot be empty");
        }

        BigDecimal calculatedSalary = getSalaryFromAdmissionDate(admissionDate);

        switch (output){
            case "full":
                return calculatedSalary;
            case "min":
                return calculatedSalary
                        .divide(minWage, RoundingMode.CEILING)
                        .setScale(2, RoundingMode.CEILING);
            default:
                throw new IllegalArgumentException("Invalid output: " + output);
        }
    }

    public BigDecimal getSalaryFromAdmissionDate(LocalDate admissionDate){
        long elapsedTimeInYears = ChronoUnit.YEARS.between(admissionDate, LocalDate.now());

        BigDecimal salary = initialSalary;
        for (long i = 0L; i < elapsedTimeInYears; i++) {
            salary = salary.multiply(new BigDecimal("1.18")).add(new BigDecimal(500));
        }

        return salary.setScale(2, RoundingMode.HALF_EVEN);
    }
}
