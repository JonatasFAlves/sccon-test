package br.com.sccon.geospatial.service.person;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AgeFormatterService {
    public Optional<Long> format(LocalDate birthDate, String output) throws Exception {
        if(!StringUtils.hasText(output)){
            throw new Exception();
        }
        switch (output) {
            case "days":
                return Optional.of(ChronoUnit.DAYS.between(birthDate, LocalDate.now()));
            case "months":
                return Optional.of(ChronoUnit.MONTHS.between(birthDate, LocalDate.now()));
            case "years":
                return Optional.of(ChronoUnit.YEARS.between(birthDate, LocalDate.now()));
            default:
                throw new Exception();
        }
    }
}
