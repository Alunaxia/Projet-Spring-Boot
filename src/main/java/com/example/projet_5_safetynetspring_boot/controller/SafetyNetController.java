package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.SafetyNetService;

@RestController
public class SafetyNetController {

    private static final Logger logger =
            LoggerFactory.getLogger(SafetyNetController.class);

    private final SafetyNetService safetyNetService;

    public SafetyNetController(SafetyNetService safetyNetService) {
        this.safetyNetService = safetyNetService;
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        logger.info("GET /persons");

        List<Person> persons = safetyNetService.getPersons();

        logger.info("GET /persons - {} personnes retournées", persons.size());

        return persons;
    }

    @GetMapping("/firestations")
    public List<FireStation> getFirestations() {
        logger.info("GET /firestations");

        List<FireStation> firestations = safetyNetService.getFirestations();

        logger.info("GET /firestations - {} casernes retournées", firestations.size());

        return firestations;
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecord> getMedicalrecords() {
        logger.info("GET /medicalrecords");

        List<MedicalRecord> medicalrecords = safetyNetService.getMedicalrecords();

        logger.info("GET /medicalrecords - {} dossiers médicaux retournés", medicalrecords.size());

        return medicalrecords;
    }
}