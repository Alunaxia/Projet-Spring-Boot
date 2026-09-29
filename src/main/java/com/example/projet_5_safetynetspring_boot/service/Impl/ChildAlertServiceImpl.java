package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.dto.ChildAlertDto;
import com.example.projet_5_safetynetspring_boot.dto.ChildAlertHouseholdMemberDto;
import com.example.projet_5_safetynetspring_boot.dto.ChildAlertPersonDto;
import com.example.projet_5_safetynetspring_boot.model.*;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.ChildAlertService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildAlertServiceImpl implements ChildAlertService {

    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;
    private static final Logger logger =
            LoggerFactory.getLogger(ChildAlertServiceImpl.class);

    public ChildAlertServiceImpl(
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService) {

        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
    }

    @Override
    public ChildAlertDto getChildAlertResponse(String address) {

        logger.debug(
                "Recherche des personnes à l'adresse {}",
                address
        );

        List<Person> persons = personService.getPersons();

        List<Person> personsAtAddress = persons.stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();

        logger.debug(
                "{} personne(s) trouvée(s) à l'adresse {}",
                personsAtAddress.size(),
                address
        );

        List<ChildAlertPersonDto> children = new ArrayList<>();

        for (Person person : personsAtAddress) {
            MedicalRecord medicalRecord =
                    medicalRecordService.getMedicalRecordForPerson(person);

            int age = ageService.getAge(medicalRecord.getBirthdate());

            if (age <= 18) {
                ChildAlertPersonDto child = new ChildAlertPersonDto();

                child.setFirstName(person.getFirstName());
                child.setLastName(person.getLastName());
                child.setAge(age);

                child.setHouseholdMembers(
                        getHouseholdMembers(person, personsAtAddress)
                );

                children.add(child);
            }
        }

        ChildAlertDto response = new ChildAlertDto();

        response.setChildren(children);

        logger.debug(
                "{} enfant(s) trouvé(s) à l'adresse {}",
                children.size(),
                address
        );

        return response;
    }

    private List<ChildAlertHouseholdMemberDto> getHouseholdMembers(
            Person child,
            List<Person> personsAtAddress) {

        List<ChildAlertHouseholdMemberDto> householdMembers =
                new ArrayList<>();

        for (Person person : personsAtAddress) {
            if (person.getFirstName().equals(child.getFirstName())
                    && person.getLastName().equals(child.getLastName())) {
                continue;
            }

            ChildAlertHouseholdMemberDto householdMember =
                    new ChildAlertHouseholdMemberDto();

            householdMember.setFirstName(person.getFirstName());
            householdMember.setLastName(person.getLastName());

            householdMembers.add(householdMember);
        }

        return householdMembers;
    }
}