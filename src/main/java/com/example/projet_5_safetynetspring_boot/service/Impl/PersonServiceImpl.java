package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.PersonRepository;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        try {
            return personRepository.getPersons();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les personnes", e);
        }
    }

    @Override
    public Person addPerson(Person person) {
        try {
            return personRepository.addPerson(person);
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'ajouter la personne", e);
        }
    }

    @Override
    public Person updatePerson(Person person) {
        try {
            return personRepository.updatePerson(person);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de modifier la personne",
                    e
            );
        }
    }

    @Override
    public void deletePerson(String firstName, String lastName) {
        try {
            personRepository.deletePerson(firstName, lastName);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de supprimer la personne",
                    e
            );
        }
    }
}