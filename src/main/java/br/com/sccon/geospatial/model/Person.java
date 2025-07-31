package br.com.sccon.geospatial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @PastOrPresent
    private LocalDate birthDate;
    @NotNull
    @PastOrPresent
    private LocalDate admissionDate;

}
