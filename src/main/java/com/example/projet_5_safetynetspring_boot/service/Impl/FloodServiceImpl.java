package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.dto.FloodHouseholdDto;
import com.example.projet_5_safetynetspring_boot.dto.FloodPersonDto;
import com.example.projet_5_safetynetspring_boot.dto.FloodDto;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.FloodService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodServiceImpl implements FloodService {

    private final FireStationService fireStationService;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;
    private static final Logger logger =
            LoggerFactory.getLogger(FloodServiceImpl.class);

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
    public FloodDto getFloodResponse(List<String> stations) {

        logger.debug(
                "Recherche des adresses associées aux casernes {}",
                stations
        );

        List<String> addresses = new ArrayList<>();

        for (String station : stations) {
            addresses.addAll(
                    fireStationService.getAddressesByStation(station)
            );
        }

        logger.debug(
                "{} adresse(s) trouvée(s) pour les casernes {}",
                addresses.size(),
                stations
        );

        List<FloodHouseholdDto> households = new ArrayList<>();

        for (String address : addresses) {

            List<FloodPersonDto> personResponses = new ArrayList<>();

            for (Person person : personService.getPersons()) {

                if (!person.getAddress().equals(address)) {
                    continue;
                }

                MedicalRecord medicalRecord =
                        medicalRecordService.getMedicalRecordForPerson(person);

                FloodPersonDto response = new FloodPersonDto();

                response.setFirstName(person.getFirstName());
                response.setLastName(person.getLastName());
                response.setPhone(person.getPhone());
                response.setAge(ageService.getAge(medicalRecord.getBirthdate()));
                response.setMedications(medicalRecord.getMedications());
                response.setAllergies(medicalRecord.getAllergies());

                personResponses.add(response);
            }

            logger.debug(
                    "{} personne(s) trouvée(s) à l'adresse {}",
                    personResponses.size(),
                    address
            );

            FloodHouseholdDto household = new FloodHouseholdDto();

            household.setAddress(address);
            household.setPersons(personResponses);

            households.add(household);
        }

        FloodDto response = new FloodDto();
        response.setHouseholds(households);

        logger.debug(
                "{} foyer(s) trouvé(s) pour les casernes {}",
                households.size(),
                stations
        );

        return response;
    }
}