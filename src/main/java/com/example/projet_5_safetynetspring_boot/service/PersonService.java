package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();

    Person addPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(String firstName, String lastName);
}