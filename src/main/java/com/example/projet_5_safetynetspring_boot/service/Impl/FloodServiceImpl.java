package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.FloodHouseholdResponse;
import com.example.projet_5_safetynetspring_boot.model.FloodPersonResponse;
import com.example.projet_5_safetynetspring_boot.model.FloodResponse;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.FloodService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodServiceImpl implements FloodService {

    private final FireStationService fireStationService;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;

    public FloodServiceImpl(
            FireStationService fireStationService,
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService) {

        this.fireStationService = fireStationService;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
    }

    @Override
    public FloodResponse getFloodResponse(List<String> stations) {

        List<String> addresses = new ArrayList<>();

        for (String station : stations) {
            addresses.addAll(
                    fireStationService.getAddressesByStation(station)
            );
        }

        List<FloodHouseholdResponse> households = new ArrayList<>();

        for (String address : addresses) {

            List<FloodPersonResponse> personResponses = new ArrayList<>();

            for (Person person : personService.getPersons()) {

                if (!person.getAddress().equals(address)) {
                    continue;
                }

                MedicalRecord medicalRecord =
                        medicalRecordService.getMedicalRecordForPerson(person);

                FloodPersonResponse response = new FloodPersonResponse();

                response.setFirstName(person.getFirstName());
                response.setLastName(person.getLastName());
                response.setPhone(person.getPhone());
                response.setAge(ageService.getAge(medicalRecord.getBirthdate()));
                response.setMedications(medicalRecord.getMedications());
                response.setAllergies(medicalRecord.getAllergies());

                personResponses.add(response);
            }

            FloodHouseholdResponse household = new FloodHouseholdResponse();

            household.setAddress(address);
            household.setPersons(personResponses);

            households.add(household);
        }

        FloodResponse response = new FloodResponse();
        response.setHouseholds(households);

        return response;
    }
}