package br.com.sccon.geospatial.service;

import br.com.sccon.geospatial.dto.person.PersonDTO;
import br.com.sccon.geospatial.dto.person.PersonDTOToPersonConverter;
import br.com.sccon.geospatial.dto.person.PersonToPersonDTOConverter;
import br.com.sccon.geospatial.model.Person;
import br.com.sccon.geospatial.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonToPersonDTOConverter personToPersonDTOConverter;
    private final PersonDTOToPersonConverter personDTOToPersonConverter;

    public PersonService(PersonRepository personRepository, PersonToPersonDTOConverter personToPersonDTOConverter, PersonDTOToPersonConverter personDTOToPersonConverter) {
        this.personRepository = personRepository;
        this.personToPersonDTOConverter = personToPersonDTOConverter;
        this.personDTOToPersonConverter = personDTOToPersonConverter;
    }

    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAllByOrderByNameAsc();
        return personList.stream().map(personToPersonDTOConverter::convert).toList();
    }

    private Optional<PersonDTO> convert(Optional<Person> optionalPerson){
        if (optionalPerson.isEmpty()) {
            return Optional.empty();
        }
        PersonDTO result = personToPersonDTOConverter.convert(optionalPerson.get());
        if (ObjectUtils.isEmpty(result)) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public Optional<PersonDTO> findById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return convert(optionalPerson);
    }

    public Optional<PersonDTO> save(PersonDTO personDTO) {
        Person person = personDTOToPersonConverter.convert(personDTO);
        if(ObjectUtils.isEmpty(person)){
            return Optional.empty();
        }
        person = personRepository.save(person);
        return convert(Optional.of(person));
    }

    public Optional<PersonDTO> delete(Long id) {
        Optional<Person>  personOpt = personRepository.findById(id);
        if(personOpt.isEmpty()){
            return Optional.empty();
        }
        personRepository.deleteById(id);
        return convert(personOpt);
    }

    public Optional<PersonDTO> update(PersonDTO personDTO) {
        Optional<PersonDTO> personDTOOptional = this.findById(personDTO.getId());
        if(personDTOOptional.isEmpty()){
            return Optional.empty();
        }
        Person person = personDTOToPersonConverter.convert(personDTO);
        if(ObjectUtils.isEmpty(person)){
            return Optional.empty();
        }
        Person save = personRepository.save(person);
        return convert(Optional.of(save));
    }

//    public Optional<Long> getAge(Long id, String output) throws Exception {
//        Optional<PersonDTO> personDTOOptional = findById(id);
//        if(!StringUtils.hasText(output)){
//            throw new Exception();
//        }
//        if(personDTOOptional.isEmpty()){
//            return Optional.empty();
//        }
//        LocalDate birth = personDTOOptional.get().getBirthDate();
//        switch (output) {
//            case "days":
//                return Optional.of(ageService.getElapsedTimeInDays(birth));
//            case "months":
//                return Optional.of(ageService.getElapsedTimeInMonths(birth));
//            case "years":
//                return Optional.of(ageService.getElapsedTimeInYears(birth));
//            default:
//                throw new Exception();
//        }
//    }
}
