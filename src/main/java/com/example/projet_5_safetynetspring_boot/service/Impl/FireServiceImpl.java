package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.FirePersonResponse;
import com.example.projet_5_safetynetspring_boot.model.FireResponse;
import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.FireService;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireServiceImpl implements FireService {

    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;
    private final FireStationService fireStationService;

    public FireServiceImpl(
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService,
            FireStationService fireStationService) {

        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
        this.fireStationService = fireStationService;
    }

    @Override
    public FireResponse getFireResponse(String address) {

        List<FirePersonResponse> personResponses = new ArrayList<>();

        for (Person person : personService.getPersons()) {

            if (!person.getAddress().equals(address)) {
                continue;
            }

            MedicalRecord medicalRecord =
                    medicalRecordService.getMedicalRecordForPerson(person);

            FirePersonResponse response = new FirePersonResponse();

            response.setFirstName(person.getFirstName());
            response.setLastName(person.getLastName());
            response.setPhone(person.getPhone());
            response.setAge(ageService.getAge(medicalRecord.getBirthdate()));
            response.setMedications(medicalRecord.getMedications());
            response.setAllergies(medicalRecord.getAllergies());

            personResponses.add(response);
        }

        String stationNumber = null;

        for (FireStation fireStation : fireStationService.getFirestations()) {
            if (fireStation.getAddress().equals(address)) {
                stationNumber = fireStation.getStation();
                break;
            }
        }

        FireResponse response = new FireResponse();

        response.setPersons(personResponses);
        response.setStationNumber(stationNumber);

        return response;
    }
}