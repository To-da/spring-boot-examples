package com.springframework.clirunnerexample.person;

/**
 * Factory to simplifying creation of {@link com.springframework.clirunnerexample.person.Person} domain object
 */
@FunctionalInterface
public interface PersonFactory<P extends Person> {
    P create(String name, Integer age);
}
