package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.dto.FireStationDto;
import com.example.projet_5_safetynetspring_boot.dto.FireStationPersonDto;
import com.example.projet_5_safetynetspring_boot.model.*;
import com.example.projet_5_safetynetspring_boot.repository.FireStationRepository;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationServiceImpl implements FireStationService {

    private final FireStationRepository fireStationRepository;
    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;
    private final AgeService ageService;
    private static final Logger logger =
            LoggerFactory.getLogger(FireStationServiceImpl.class);

    public FireStationServiceImpl(
            FireStationRepository fireStationRepository,
            PersonService personService,
            MedicalRecordService medicalRecordService,
            AgeService ageService) {

        this.fireStationRepository = fireStationRepository;
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.ageService = ageService;
    }

    @Override
    public List<FireStation> getFirestations() {
        try {
            return fireStationRepository.getFirestations();
        } catch (IOException e) {
            logger.error("Erreur lors de la lecture des casernes", e);
            throw new RuntimeException("Impossible de lire les casernes", e);
        }
    }

    @Override
    public List<String> getAddressesByStation(String stationNumber) {
        try {
            return fireStationRepository.getFirestations()
                    .stream()
                    .filter(fireStation -> fireStation.getStation().equals(stationNumber))
                    .map(FireStation::getAddress)
                    .toList();
        } catch (IOException e) {
            logger.error(
                    "Erreur lors de la récupération des adresses de la caserne {}",
                    stationNumber,
                    e
            );

            throw new RuntimeException(
                    "Impossible de lire les casernes",
                    e
            );
        }
    }

    @Override
    public List<Person> getPersonsByAddresses(List<String> addresses) {
        List<Person> persons = new ArrayList<>();

        for (Person person : personService.getPersons()) {
            if (addresses.contains(person.getAddress())) {
                persons.add(person);
            }
        }

        return persons;
    }

    @Override
    public FireStationDto getFireStationResponse(String stationNumber) {

        logger.debug(
                "Recherche des adresses associées à la caserne {}",
                stationNumber
        );

        List<String> addresses = getAddressesByStation(stationNumber);

        logger.debug(
                "{} adresse(s) trouvée(s) pour la caserne {}",
                addresses.size(),
                stationNumber
        );

        List<Person> persons = getPersonsByAddresses(addresses);

        logger.debug(
                "{} personne(s) trouvée(s) aux adresses de la caserne {}",
                persons.size(),
                stationNumber
        );

        List<FireStationPersonDto> personResponses = new ArrayList<>();
        int adultCount = 0;
        int childCount = 0;

        for (Person person : persons) {
            MedicalRecord medicalRecord =
                    medicalRecordService.getMedicalRecordForPerson(person);

            int age = ageService.getAge(medicalRecord.getBirthdate());

            if (age <= 18) {
                childCount++;
            } else {
                adultCount++;
            }

            FireStationPersonDto response = new FireStationPersonDto();

            response.setFirstName(person.getFirstName());
            response.setLastName(person.getLastName());
            response.setAddress(person.getAddress());
            response.setPhone(person.getPhone());

            personResponses.add(response);
        }

        FireStationDto response = new FireStationDto();

        response.setPersons(personResponses);
        response.setAdultCount(adultCount);
        response.setChildCount(childCount);

        return response;
    }

    @Override
    public FireStation addFireStation(FireStation fireStation) {
        try {
            return fireStationRepository.addFireStation(fireStation);
        } catch (IOException e) {
            logger.error(
                    "Erreur lors de l'ajout de la caserne à l'adresse {}",
                    fireStation.getAddress(),
                    e
            );

            throw new RuntimeException(
                    "Impossible d'ajouter la caserne",
                    e
            );
        }
    }

    @Override
    public FireStation updateFireStation(FireStation fireStation) {
        try {
            return fireStationRepository.updateFireStation(fireStation);
        } catch (IOException e) {
            logger.error(
                    "Erreur lors de la modification de la caserne à l'adresse {}",
                    fireStation.getAddress(),
                    e
            );

            throw new RuntimeException(
                    "Impossible de modifier la caserne",
                    e
            );
        }
    }

    @Override
    public void deleteFireStation(String address) {
        try {
            fireStationRepository.deleteFireStation(address);
        } catch (IOException e) {
            logger.error(
                    "Erreur lors de la suppression de la caserne à l'adresse {}",
                    address,
                    e
            );

            throw new RuntimeException(
                    "Impossible de supprimer la caserne",
                    e
            );
        }
    }
}