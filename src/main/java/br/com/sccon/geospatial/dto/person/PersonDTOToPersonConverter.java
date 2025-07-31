package br.com.sccon.geospatial.dto.person;

import br.com.sccon.geospatial.model.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class PersonDTOToPersonConverter implements Converter<PersonDTO, Person> {

    @Override
    public Person convert(PersonDTO source) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }

        Person person = new Person();
        person.setId(source.getId());
        person.setName(source.getName());
        person.setBirthDate(source.getBirthDate());
        person.setAdmissionDate(source.getAdmissionDate());
        return person;
    }
}
