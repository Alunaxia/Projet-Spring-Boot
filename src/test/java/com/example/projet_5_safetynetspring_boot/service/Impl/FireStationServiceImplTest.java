package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.dto.FireStationDto;
import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.MedicalRecord;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.repository.FireStationRepository;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.MedicalRecordService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FireStationServiceImplTest {

    private FireStationRepository fireStationRepository;
    private PersonService personService;
    private MedicalRecordService medicalRecordService;
    private AgeService ageService;
    private FireStationServiceImpl service;

    @BeforeEach
    void setUp() {
        fireStationRepository = mock(FireStationRepository.class);
        personService = mock(PersonService.class);
        medicalRecordService = mock(MedicalRecordService.class);
        ageService = mock(AgeService.class);

        service = new FireStationServiceImpl(
                fireStationRepository,
                personService,
                medicalRecordService,
                ageService
        );
    }

    @Test
    void getAddressesByStation() throws IOException {

        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation("3");

        when(fireStationRepository.getFirestations())
                .thenReturn(List.of(fireStation));

        List<String> addresses =
                service.getAddressesByStation("3");

        assertEquals(List.of("1509 Culver St"), addresses);
    }

    @Test
    void getPersonsByAddresses() {
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Boyd");
        person1.setAddress("1509 Culver St");

        Person person2 = new Person();
        person2.setFirstName("Peter");
        person2.setLastName("Duncan");
        person2.setAddress("644 Gershwin Cir");

        when(personService.getPersons())
                .thenReturn(List.of(person1, person2));

        List<Person> persons = service.getPersonsByAddresses(
                List.of("1509 Culver St")
        );

        assertEquals(1, persons.size());
        assertEquals("John", persons.get(0).getFirstName());
        assertEquals("Boyd", persons.get(0).getLastName());
    }

    @Test
    void getFireStationResponse() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation("3");

        Person adult = new Person();
        adult.setFirstName("John");
        adult.setLastName("Boyd");
        adult.setAddress("1509 Culver St");
        adult.setPhone("841-874-6512");

        Person child = new Person();
        child.setFirstName("Tenley");
        child.setLastName("Boyd");
        child.setAddress("1509 Culver St");
        child.setPhone("841-874-6512");

        MedicalRecord adultRecord = new MedicalRecord();
        adultRecord.setBirthdate("03/06/1984");

        MedicalRecord childRecord = new MedicalRecord();
        childRecord.setBirthdate("02/18/2012");

        when(fireStationRepository.getFirestations())
                .thenReturn(List.of(fireStation));

        when(personService.getPersons())
                .thenReturn(List.of(adult, child));

        when(medicalRecordService.getMedicalRecordForPerson(adult))
                .thenReturn(adultRecord);

        when(medicalRecordService.getMedicalRecordForPerson(child))
                .thenReturn(childRecord);

        when(ageService.getAge("03/06/1984"))
                .thenReturn(42);

        when(ageService.getAge("02/18/2012"))
                .thenReturn(14);

        FireStationDto response =
                service.getFireStationResponse("3");

        assertEquals(1, response.getAdultCount());
        assertEquals(1, response.getChildCount());
        assertEquals(2, response.getPersons().size());
    }

    @Test
    void getFirestations() throws IOException {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1509 Culver St");
        fireStation.setStation("3");

        when(fireStationRepository.getFirestations())
                .thenReturn(List.of(fireStation));

        List<FireStation> fireStations =
                service.getFirestations();

        assertEquals(1, fireStations.size());
        assertEquals("1509 Culver St", fireStations.get(0).getAddress());
        assertEquals("3", fireStations.get(0).getStation());
    }

    @Test
    void getFirestationsWithRepositoryError() throws IOException {
        when(fireStationRepository.getFirestations())
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.getFirestations()
        );
    }

    @Test
    void getAddressesByStationWithRepositoryError() throws IOException {
        when(fireStationRepository.getFirestations())
                .thenThrow(new IOException("Erreur de test"));

        assertThrows(
                RuntimeException.class,
                () -> service.getAddressesByStation("3")
        );
    }
}