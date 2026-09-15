package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    private static final Logger logger =
            LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getMedicalrecords() {
        logger.info("GET /medicalRecord");

        List<MedicalRecord> medicalRecord =
                medicalRecordService.getMedicalrecords();

        logger.info("GET /medicalRecord - {} dossiers médicaux retournés", medicalRecord.size());

        return medicalRecord;
    }

    @PostMapping("/medicalRecord")
    public MedicalRecord addMedicalRecord(
            @RequestBody MedicalRecord medicalRecord) {

        logger.info(
                "POST /medicalRecord - {} {}",
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );

        MedicalRecord addedMedicalRecord =
                medicalRecordService.addMedicalRecord(medicalRecord);

        logger.info(
                "POST /medicalRecord - dossier médical de {} {} ajouté",
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );

        return addedMedicalRecord;
    }

    @PutMapping("/medicalRecord")
    public MedicalRecord updateMedicalRecord(
            @RequestBody MedicalRecord medicalRecord) {

        logger.info(
                "PUT /medicalRecord - {} {}",
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );

        MedicalRecord updatedMedicalRecord =
                medicalRecordService.updateMedicalRecord(medicalRecord);

        logger.info(
                "PUT /medicalRecord - dossier médical de {} {} modifié",
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );

        return updatedMedicalRecord;
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(
            @RequestParam String firstName,
            @RequestParam String lastName) {

        logger.info(
                "DELETE /medicalRecord?firstName={}&lastName={}",
                firstName,
                lastName
        );

        medicalRecordService.deleteMedicalRecord(firstName, lastName);

        logger.info(
                "DELETE /medicalRecord - dossier médical de {} {} supprimé",
                firstName,
                lastName
        );
    }
}
