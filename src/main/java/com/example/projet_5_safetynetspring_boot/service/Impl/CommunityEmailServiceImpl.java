package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.CommunityEmailService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityEmailServiceImpl implements CommunityEmailService {

    private final PersonService personService;

    public CommunityEmailServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public List<String> getEmailsByCity(String city) {

        List<String> emails = new ArrayList<>();

        for (Person person : personService.getPersons()) {

            if (person.getCity().equals(city)) {
                emails.add(person.getEmail());
            }
        }

        return emails;
    }
}