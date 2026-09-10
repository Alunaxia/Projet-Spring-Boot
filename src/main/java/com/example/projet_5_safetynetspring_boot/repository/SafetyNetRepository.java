package com.example.projet_5_safetynetspring_boot.repository;

import com.example.projet_5_safetynetspring_boot.model.Data;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.model.FireStation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Repository
public class SafetyNetRepository {

    private final ObjectMapper objectMapper;

    public SafetyNetRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Person> getPersons() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getPersons();
    }

    public List<FireStation> getFirestations() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getFirestations();
    }

    public List<MedicalRecord> getMedicalrecords() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getMedicalrecords();
    }
}
