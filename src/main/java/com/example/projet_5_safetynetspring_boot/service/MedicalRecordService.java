package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecord> getMedicalrecords();

    MedicalRecord getMedicalRecordForPerson(Person person);

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(String firstName, String lastName);
}