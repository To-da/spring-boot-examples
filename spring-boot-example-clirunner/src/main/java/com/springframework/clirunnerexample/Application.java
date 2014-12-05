package com.springframework.clirunnerexample;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.springframework.clirunnerexample.person.Person;
import com.springframework.clirunnerexample.person.PersonRepository;

@SpringBootApplication
public class Application {

    static Logger logger = LoggerFactory.getLogger(Application.class);

    @Order(Ordered.HIGHEST_PRECEDENCE+1000)
    @Bean
    CommandLineRunner argProcessingCommandLineRunner() {
        return (args) -> {
            //filter optionArgs (processed by te spring boot) - logic from {@link SimpleCommandLineArgsParser#parse}
            args = Stream.of(args).filter(arg -> !arg.startsWith("--")).toArray(String[]::new);

            // do with args whatever we need -> for example print them
            Stream.of(args).forEach(arg -> logger.info("On of the input args is: {}", arg));
        };
    }

    @Order(Ordered.LOWEST_PRECEDENCE-1000)
    @Bean
    CommandLineRunner processBeforeStart(PersonRepository personRepository) {
        return (callback) -> Arrays.asList("Panda Makrova:5;Wanda Trollowa:63;Sigma Alfova:18".split(";")).forEach(
                entry -> {
                    String[] nameAndAge = entry.split(":");
                    String name = nameAndAge[0];
                    String age = nameAndAge[1];
                    Person person = Person.getFactory().create(name, Integer.valueOf(age));
                    personRepository.save(person);
                    logger.info("Person {} inserted to DB", person);
                }
        );
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).run(args);
        final Optional<Person> panda = context.getBean(PersonRepository.class).findByNameAndAge("Panda Makrova", 5); //TODO create crash command & REST endpoint
        logger.info("Is present? : {}", panda.isPresent());
        logger.info("Value : {}", panda.orElseGet(() -> Person.getFactory().create("default", 0)));
    }



}
