package br.com.sccon.geospatial.dto.person;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonDTO {
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
