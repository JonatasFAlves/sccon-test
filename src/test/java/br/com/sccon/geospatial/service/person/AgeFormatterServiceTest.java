package br.com.sccon.geospatial.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgeFormatterServiceTest {

    private AgeFormatterService ageFormatterService;

    @BeforeEach
    void setUp() {
        ageFormatterService = new AgeFormatterService();
    }

    @Test
    void testFormatInDays() throws IllegalArgumentException {
        LocalDate birthDate = LocalDate.now().minusDays(100);
        Optional<Long> result = ageFormatterService.format(birthDate, "days");

        assertTrue(result.isPresent());
        assertEquals(100L, result.get());
    }

    @Test
    void testFormatInMonths() throws IllegalArgumentException {
        LocalDate birthDate = LocalDate.now().minusMonths(5);
        Optional<Long> result = ageFormatterService.format(birthDate, "months");

        assertTrue(result.isPresent());
        assertEquals(5L, result.get());
    }

    @Test
    void testFormatInYears() throws IllegalArgumentException {
        LocalDate birthDate = LocalDate.now().minusYears(10);
        Optional<Long> result = ageFormatterService.format(birthDate, "years");

        assertTrue(result.isPresent());
        assertEquals(10L, result.get());
    }

    @Test
    void testFormatNow() throws IllegalArgumentException {
        LocalDate birthDate = LocalDate.now();
        Optional<Long> result = ageFormatterService.format(birthDate, "years");

        assertTrue(result.isPresent());
        assertEquals(0L, result.get());
    }

    @Test
    void testFormatWithEmptyOutputThrowsException() {
        LocalDate birthDate = LocalDate.now().minusYears(10);
        assertThrows(IllegalArgumentException.class, () -> {
            ageFormatterService.format(birthDate, "");
        });
    }

    @Test
    void testFormatWithInvalidOutputThrowsException() {
        LocalDate birthDate = LocalDate.now().minusYears(10);
        assertThrows(IllegalArgumentException.class, () -> {
            ageFormatterService.format(birthDate, "invalidOutput");
        });
    }
}
