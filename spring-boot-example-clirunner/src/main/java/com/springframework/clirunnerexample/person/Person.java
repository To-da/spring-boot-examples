package com.springframework.clirunnerexample.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;

    /** for JPA */
    Person() {
    }

    Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Create getFactory with method reference
     * Could be also like:  (name, age) -> new Person(name, age)
     * @return
     */
    public static PersonFactory getFactory() {
        PersonFactory<Person> personFactory = Person::new;
        return personFactory;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

