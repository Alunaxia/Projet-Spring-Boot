package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.MedicalRecordRepository;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getMedicalrecords() {
        try {
            return medicalRecordRepository.getMedicalrecords();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de lire les dossiers médicaux",
                    e
            );
        }
    }

    @Override
    public MedicalRecord getMedicalRecordForPerson(Person person) {
        try {
            for (MedicalRecord medicalRecord : medicalRecordRepository.getMedicalrecords()) {
                if (medicalRecord.getFirstName().equals(person.getFirstName())
                        && medicalRecord.getLastName().equals(person.getLastName())) {
                    return medicalRecord;
                }
            }

            return null;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de lire les dossiers médicaux",
                    e
            );
        }
    }

    @Override
    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            return medicalRecordRepository.addMedicalRecord(medicalRecord);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible d'ajouter le dossier médical",
                    e
            );
        }
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        try {
            return medicalRecordRepository.updateMedicalRecord(medicalRecord);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de modifier le dossier médical",
                    e
            );
        }
    }

    @Override
    public void deleteMedicalRecord(String firstName, String lastName) {
        try {
            medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Impossible de supprimer le dossier médical",
                    e
            );
        }
    }
}