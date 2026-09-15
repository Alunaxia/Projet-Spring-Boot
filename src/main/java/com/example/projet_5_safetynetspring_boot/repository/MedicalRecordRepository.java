package com.example.projet_5_safetynetspring_boot.repository;

import com.example.projet_5_safetynetspring_boot.model.Data;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private final ObjectMapper objectMapper;

    public MedicalRecordRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<MedicalRecord> getMedicalrecords() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getMedicalrecords();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getMedicalrecords().add(medicalRecord);

        objectMapper.writeValue(resource.getFile(), data);

        return medicalRecord;
    }

    public MedicalRecord updateMedicalRecord(
            MedicalRecord updatedMedicalRecord) throws IOException {

        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        for (MedicalRecord medicalRecord : data.getMedicalrecords()) {

            if (medicalRecord.getFirstName().equals(updatedMedicalRecord.getFirstName())
                    && medicalRecord.getLastName().equals(updatedMedicalRecord.getLastName())) {

                medicalRecord.setBirthdate(updatedMedicalRecord.getBirthdate());
                medicalRecord.setMedications(updatedMedicalRecord.getMedications());
                medicalRecord.setAllergies(updatedMedicalRecord.getAllergies());

                objectMapper.writeValue(resource.getFile(), data);

                return medicalRecord;
            }
        }

        return null;
    }

    public void deleteMedicalRecord(
            String firstName,
            String lastName) throws IOException {

        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getMedicalrecords().removeIf(
                medicalRecord ->
                        medicalRecord.getFirstName().equals(firstName)
                                && medicalRecord.getLastName().equals(lastName)
        );

        objectMapper.writeValue(resource.getFile(), data);
    }
}