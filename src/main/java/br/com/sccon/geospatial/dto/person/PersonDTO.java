package br.com.sccon.geospatial.dto.person;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private LocalDate admissionDate;
}
