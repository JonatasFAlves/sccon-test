package br.com.sccon.geospatial.dto.person;

import br.com.sccon.geospatial.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class PersonToPersonDTOConverter implements Converter<Person, PersonDTO> {
    @Override
    public PersonDTO convert(Person source) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }
        return PersonDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .birthDate(source.getBirthDate())
                .admissionDate(source.getAdmissionDate())
                .build();
    }
}
