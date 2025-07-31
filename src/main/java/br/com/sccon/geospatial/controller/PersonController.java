package br.com.sccon.geospatial.controller;

import br.com.sccon.geospatial.dto.person.PersonDTO;
import br.com.sccon.geospatial.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping()
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        Optional<PersonDTO> optionalPersonDTO = personService.save(personDTO);
        if(optionalPersonDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> delete(@PathVariable("id") String id){
        Optional<PersonDTO> deletedPerson = personService.delete(Long.valueOf(id));
        return deletedPerson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody PersonDTO personDTO, @PathVariable("id") String id){
        personDTO.setId(Long.valueOf(id));
        Optional<PersonDTO> updatedPerson = personService.update(personDTO);
        if(updatedPerson.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateAttribute(@RequestBody PersonDTO personDTO, @PathVariable("id") String id){
        personDTO.setId(Long.valueOf(id));
        Optional<PersonDTO> updatedPerson = personService.update(personDTO);
        if(updatedPerson.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable("id") String id){
        Optional<PersonDTO> person = personService.findById(Long.valueOf(id));
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/age")
    public ResponseEntity<Long> getAgeById(
            @PathVariable("id") String id,
            @RequestParam("output") String output){
        Optional<PersonDTO> person = personService.findById(Long.valueOf(id));
        if(person.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        try {
            Optional<Long> age = personService.getAge(Long.valueOf(id), output);
            return age.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (OutputFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<BigDecimal> getSalary(
            @PathVariable("id") String id,
            @RequestParam("output") String output){
        Optional<PersonDTO> person = personService.findById(Long.valueOf(id));
        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            BigDecimal salary = salaryService.getSalary(output, person.get());
            return ResponseEntity.ok(salary);
        } catch (OutputFormatException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
