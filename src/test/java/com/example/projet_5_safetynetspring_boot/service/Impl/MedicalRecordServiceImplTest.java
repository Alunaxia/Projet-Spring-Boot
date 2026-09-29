package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MedicalRecordServiceImplTest {

    private MedicalRecordRepository medicalRecordRepository;
    private MedicalRecordServiceImpl service;

    @BeforeEach
    void setUp() {
        medicalRecordRepository = mock(MedicalRecordRepository.class);
        service = new MedicalRecordServiceImpl(medicalRecordRepository);
    }

    @Test
    void getMedicalrecords() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        when(medicalRecordRepository.getMedicalrecords())
                .thenReturn(List.of(medicalRecord));

        List<MedicalRecord> records =
                service.getMedicalrecords();

        assertEquals(1, records.size());
        assertEquals("John", records.get(0).getFirstName());
        assertEquals("Boyd", records.get(0).getLastName());
        assertEquals("03/06/1984", records.get(0).getBirthdate());
    }

    @Test
    void getMedicalRecordForPerson() throws IOException {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Boyd");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("03/06/1984");

        when(medicalRecordRepository.getMedicalrecords())
                .thenReturn(List.of(medicalRecord));

        MedicalRecord result =
                service.getMedicalRecordForPerson(person);

        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
        assertEquals("03/06/1984", result.getBirthdate());
    }

    @Test
    void getMedicalRecordForPersonNotFound() throws IOException {
        Person person = new Person();
        person.setFirstName("Unknown");
        person.setLastName("Person");

        when(medicalRecordRepository.getMedicalrecords())
                .thenReturn(List.of());

        MedicalRecord result =
                service.getMedicalRecordForPerson(person);

        assertNull(result);
    }

    @Test
    void getMedicalrecordsWithRepositoryError() throws IOException {
        when(medicalRecordRepository.getMedicalrecords())
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.getMedicalrecords()
        );
    }

    @Test
    void addMedicalRecord() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Test");
        medicalRecord.setLastName("Medical");
        medicalRecord.setBirthdate("01/01/2000");

        when(medicalRecordRepository.addMedicalRecord(medicalRecord))
                .thenReturn(medicalRecord);

        MedicalRecord result =
                service.addMedicalRecord(medicalRecord);

        assertEquals("Test", result.getFirstName());
        assertEquals("Medical", result.getLastName());
        assertEquals("01/01/2000", result.getBirthdate());
    }

    @Test
    void addMedicalRecordWithRepositoryError() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Test");
        medicalRecord.setLastName("Medical");

        when(medicalRecordRepository.addMedicalRecord(medicalRecord))
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.addMedicalRecord(medicalRecord)
        );
    }

    @Test
    void updateMedicalRecord() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");
        medicalRecord.setBirthdate("02/02/2000");

        when(medicalRecordRepository.updateMedicalRecord(medicalRecord))
                .thenReturn(medicalRecord);

        MedicalRecord result =
                service.updateMedicalRecord(medicalRecord);

        assertEquals("John", result.getFirstName());
        assertEquals("Boyd", result.getLastName());
        assertEquals("02/02/2000", result.getBirthdate());
    }

    @Test
    void updateMedicalRecordWithRepositoryError() throws IOException {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Boyd");

        when(medicalRecordRepository.updateMedicalRecord(medicalRecord))
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.updateMedicalRecord(medicalRecord)
        );
    }

    @Test
    void deleteMedicalRecord() throws IOException {
        service.deleteMedicalRecord("John", "Boyd");
    }

    @Test
    void deleteMedicalRecordWithRepositoryError() throws IOException {
        doThrow(new IOException("Erreur de test"))
                .when(medicalRecordRepository)
                .deleteMedicalRecord("John", "Boyd");

        assertThrows(
                RuntimeException.class,
                () -> service.deleteMedicalRecord("John", "Boyd")
        );
    }
}