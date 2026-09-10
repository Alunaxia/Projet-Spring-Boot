package com.example.projet_5_safetynetspring_boot.service;
import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.SafetyNetRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class SafetyNetService {

    private final SafetyNetRepository safetyNetRepository;

    public SafetyNetService(SafetyNetRepository safetyNetRepository) {
        this.safetyNetRepository = safetyNetRepository;
    }

    public List<Person> getPersons() {
        try {
            return safetyNetRepository.getPersons();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les données", e);
        }
    }

    public List<FireStation> getFirestations() {
        try {
            return safetyNetRepository.getFirestations();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les données", e);
        }
    }

    public List<MedicalRecord> getMedicalrecords() {
        try {
            return safetyNetRepository.getMedicalrecords();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les données", e);
        }
    }
}
