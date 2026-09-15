package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.*;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.ChildAlertService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildAlertServiceImpl implements ChildAlertService {

    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;

    public ChildAlertServiceImpl(
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService) {

        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
    }

    @Override
    public ChildAlertResponse getChildAlertResponse(String address) {
        List<Person> persons = personService.getPersons();

        List<Person> personsAtAddress = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        List<ChildAlertPersonResponse> children = new ArrayList<>();

        for (Person person : personsAtAddress) {
            MedicalRecord medicalRecord =
                    medicalRecordService.getMedicalRecordForPerson(person);

            int age = ageService.getAge(medicalRecord.getBirthdate());

            if (age <= 18) {
                ChildAlertPersonResponse child = new ChildAlertPersonResponse();

                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(age);

                child.setHouseholdMembers(
                        getHouseholdMembers(person, personsAtAddress)
                );

                children.add(child);
            }
        }

        ChildAlertResponse response = new ChildAlertResponse();

        response.setChildren(children);

        return response;
    }

    private List<ChildAlertHouseholdMemberResponse> getHouseholdMembers(
            Person child,
            List<Person> personsAtAddress) {

        List<ChildAlertHouseholdMemberResponse> householdMembers =
                new ArrayList<>();

        for (Person person : personsAtAddress) {
            if (person.getFirstName().equals(child.getFirstName())
                    && person.getLastName().equals(child.getLastName())) {
                continue;
            }

            ChildAlertHouseholdMemberResponse householdMember =
                    new ChildAlertHouseholdMemberResponse();

            householdMember.setFirstName(person.getFirstName());
            householdMember.setLastName(person.getLastName());

            householdMembers.add(householdMember);
        }

        return householdMembers;
    }
}