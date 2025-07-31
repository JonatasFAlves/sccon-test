package br.com.sccon.geospatial.repository;

import br.com.sccon.geospatial.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person save(Person person);
    void deleteById(Long id);
    Optional<Person> findById(Long id);
    List<Person> findAllByOrderByNameAsc();
}
