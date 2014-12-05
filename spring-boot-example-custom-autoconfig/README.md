Autoconfig example
==================

# What is Autoconfiguration
Autoconfiguration is a feature of the [Spring Boot](http://projects.spring.io/spring-boot/ "Spring Boot project")
which can provide developers convention-over-configuration principle on the bean-registering and configuration level.

# Why Autoconfiguration
What does it mean? If you create shared library which needs registration and configuration of Spring beans you have
to explicitly provide this information to other developers (to tell them how to manually register beans or where to set up component scanning (right package of the library)).

# Spring Boot can help
With Spring Boot you can easily add auto-config dependency to the other team project and let the Spring Boot to handle the other (register
been, retrieve default properties, etc.). All you need is to add
[@EnableAutoConfig](http://docs.spring.io/autorepo/docs/spring-boot/1.2.0.RC2/api/org/springframework/boot/autoconfigure/EnableAutoConfiguration.html)
 annotation to the main config class (or [@SpringBootApplication](http://docs.spring.io/autorepo/docs/spring-boot/1.2.0.RC2/api/org/springframework/boot/autoconfigure/SpringBootApplication.html)
 hiding @Configuration, @ComponentScan and @EnableAutoConfiguration under one convenience annotation)

This is handy if you want to create project template with all auto-configurations ready and activate specific
auto-configuration only by adding specific dependency to the project (or satisfy any other condition needed
to activate autoconfig class). Project template will have parent project with dependencies to all your autoconfiguration libraries.


More description how to create own auto-configuration :
[Spring Boot autoconfig reference](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-developing-auto-configuration)


# Video
Related [youtube video](http://youtu.be/fEuXdeJPY7U)