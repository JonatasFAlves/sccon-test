package br.com.sccon.geospatial.bootstrap;

import br.com.sccon.geospatial.model.Person;
import br.com.sccon.geospatial.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class BootstrapData implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(BootstrapData.class);
    private final PersonRepository personRepository;

    public BootstrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void run(String... args) throws Exception {
        logger.info("Bootstrapping data...");
        Person person1 = new Person(null, "Jonatas", LocalDate.of(1990, 11, 9), LocalDate.of(2022, 11, 04));
        Person person2 = new Person(null, "Carlos", LocalDate.of(1996,1,14), LocalDate.of(2025, 7,31));
        Person person3 = new Person(null, "Maria", LocalDate.of(1988, 5, 23), LocalDate.of(2024, 5, 5));
        personRepository.saveAll(Arrays.asList(person1, person2, person3));
    }
}
