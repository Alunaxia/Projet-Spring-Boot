package com.example.projet_5_safetynetspring_boot.repository;

import com.example.projet_5_safetynetspring_boot.model.Data;
import com.example.projet_5_safetynetspring_boot.model.Person;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Repository
public class PersonRepository {
    private final ObjectMapper objectMapper;

    public PersonRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Person> getPersons() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getPersons();
    }

    public Person addPerson(Person person) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getPersons().add(person);

        objectMapper.writeValue(resource.getFile(), data);

        return person;
    }

    public Person updatePerson(Person updatedPerson) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        for (Person person : data.getPersons()) {
            if (person.getFirstName().equals(updatedPerson.getFirstName())
                    && person.getLastName().equals(updatedPerson.getLastName())) {

                person.setAddress(updatedPerson.getAddress());
                person.setCity(updatedPerson.getCity());
                person.setZip(updatedPerson.getZip());
                person.setPhone(updatedPerson.getPhone());
                person.setEmail(updatedPerson.getEmail());

                objectMapper.writeValue(resource.getFile(), data);

                return person;
            }
        }

        return null;
    }

    public void deletePerson(String firstName, String lastName) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getPersons().removeIf(person ->
                person.getFirstName().equals(firstName)
                        && person.getLastName().equals(lastName)
        );

        objectMapper.writeValue(resource.getFile(), data);
    }
}
