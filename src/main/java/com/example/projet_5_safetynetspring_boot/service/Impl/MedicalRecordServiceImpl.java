package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.MedicalRecordRepository;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private static final Logger logger =
            LoggerFactory.getLogger(MedicalRecordServiceImpl.class);

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> getMedicalrecords() {
        try {
            return medicalRecordRepository.getMedicalrecords();
        } catch (IOException e) {
            logger.error(
                    "Erreur lors de la lecture des dossiers médicaux",
                    e
            );

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
            logger.error(
                    "Erreur lors de la recherche du dossier médical de {} {}",
                    person.getFirstName(),
                    person.getLastName(),
                    e
            );

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
            logger.error(
                    "Erreur lors de l'ajout du dossier médical de {} {}",
                    medicalRecord.getFirstName(),
                    medicalRecord.getLastName(),
                    e
            );

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
            logger.error(
                    "Erreur lors de la modification du dossier médical de {} {}",
                    medicalRecord.getFirstName(),
                    medicalRecord.getLastName(),
                    e
            );

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
            logger.error(
                    "Erreur lors de la suppression du dossier médical de {} {}",
                    firstName,
                    lastName,
                    e
            );

            throw new RuntimeException(
                    "Impossible de supprimer le dossier médical",
                    e
            );
        }
    }
}