package com.springframework.clirunnerexample.person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository over {@link com.springframework.clirunnerexample.person.Person} domain object
 */
public interface PersonRepository  extends JpaRepository<Person, Long> {

    Optional<Person> findByNameAndAge(String name, Integer age);

}
