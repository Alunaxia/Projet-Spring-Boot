package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

class PersonServiceImplTest {

    private PersonRepository personRepository;
    private PersonServiceImpl service;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        service = new PersonServiceImpl(personRepository);
    }

    @Test
    void getPersons() throws IOException {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        when(personRepository.getPersons())
                .thenReturn(List.of(person));

        List<Person> persons = service.getPersons();

        assertEquals(1, persons.size());
        assertEquals("John", persons.get(0).getFirstName());
        assertEquals("Boyd", persons.get(0).getLastName());
    }

    @Test
    void getPersonsWithRepositoryError() throws IOException {
        when(personRepository.getPersons())
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.getPersons()
        );
    }

    @Test
    void addPerson() throws IOException {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Person");

        when(personRepository.addPerson(person))
                .thenReturn(person);

        Person result = service.addPerson(person);

        assertEquals("Test", result.getFirstName());
        assertEquals("Person", result.getLastName());
    }

    @Test
    void addPersonWithRepositoryError() throws IOException {
        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Person");

        when(personRepository.addPerson(person))
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.addPerson(person)
        );
    }

    @Test
    void updatePerson() throws IOException {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        when(personRepository.updatePerson(person))
                .thenReturn(person);

        Person result = service.updatePerson(person);

        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
    }

    @Test
    void updatePersonWithRepositoryError() throws IOException {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        when(personRepository.updatePerson(person))
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.updatePerson(person)
        );
    }

    @Test
    void deletePerson() throws IOException {
        service.deletePerson("John", "Boyd");
    }

    @Test
    void deletePersonWithRepositoryError() throws IOException {
        doThrow(new IOException("Erreur de test"))
                .when(personRepository)
                .deletePerson("John", "Boyd");

        assertThrows(
                RuntimeException.class,
                () -> service.deletePerson("John", "Boyd")
        );
    }
}