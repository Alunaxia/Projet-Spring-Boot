package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.dto.PersonInfoDto;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonInfoService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;
    private static final Logger logger =
            LoggerFactory.getLogger(PersonInfoServiceImpl.class);

    public PersonInfoServiceImpl(
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService) {

        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
    }

    @Override
    public List<PersonInfoDto> getPersonInfo(String lastName) {

        logger.debug(
                "Recherche des informations pour le nom {}",
                lastName
        );

        List<PersonInfoDto> responses = new ArrayList<>();

        for (Person person : personService.getPersons()) {

            if (!person.getLastName().equals(lastName)) {
                continue;
            }

            MedicalRecord medicalRecord =
                    medicalRecordService.getMedicalRecordForPerson(person);

            PersonInfoDto response = new PersonInfoDto();

            response.setFirstName(person.getFirstName());
            response.setLastName(person.getLastName());
            response.setAddress(person.getAddress());
            response.setAge(ageService.getAge(medicalRecord.getBirthdate()));
            response.setEmail(person.getEmail());
            response.setMedications(medicalRecord.getMedications());
            response.setAllergies(medicalRecord.getAllergies());

            responses.add(response);
        }

        logger.debug(
                "{} personne(s) trouvée(s) pour le nom {}",
                responses.size(),
                lastName
        );

        return responses;
    }
}