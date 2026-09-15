package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.ChildAlertResponse;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.Impl.PersonServiceImpl;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class PersonController {
    private static final Logger logger =
            LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> getPersons() {
        logger.info("GET /person");

        List<Person> person = personService.getPersons();

        logger.info("GET /person - {} personnes retournées", person.size());

        return person;
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {

        logger.info(
                "POST /person - ajout de {} {}",
                person.getFirstName(),
                person.getLastName()
        );

        Person addedPerson = personService.addPerson(person);

        logger.info(
                "POST /person - personne {} {} ajoutée",
                person.getFirstName(),
                person.getLastName()
        );

        return addedPerson;
    }

    @PutMapping("/person")
    public Person updatePerson(@RequestBody Person person) {

        logger.info(
                "PUT /person - {} {}",
                person.getFirstName(),
                person.getLastName()
        );

        Person updatedPerson = personService.updatePerson(person);

        logger.info(
                "PUT /person - personne {} {} modifiée",
                person.getFirstName(),
                person.getLastName()
        );

        return updatedPerson;
    }

    @DeleteMapping("/person")
    public void deletePerson(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        logger.info(
                "DELETE /person?firstName={}&lastName={}",
                firstName,
                lastName
        );

        personService.deletePerson(firstName, lastName);

        logger.info(
                "DELETE /person - personne {} {} supprimée",
                firstName,
                lastName
        );
    }
}

