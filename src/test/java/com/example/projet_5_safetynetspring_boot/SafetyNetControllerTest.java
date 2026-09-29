package com.example.projet_5_safetynetspring_boot;

import com.example.projet_5_safetynetspring_boot.model.Data;
import com.example.projet_5_safetynetspring_boot.service.AgeService;
import com.example.projet_5_safetynetspring_boot.service.Impl.AgeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void resetData() throws Exception {
        ClassPathResource source =
                new ClassPathResource("donnees-test.json");

        ClassPathResource target =
                new ClassPathResource("donnees.json");

        try (InputStream inputStream = source.getInputStream()) {
            Files.copy(
                    inputStream,
                    Path.of(target.getURI()),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );
        }
    }

    @Test
    void getPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].city").value("Culver"))
                .andExpect(jsonPath("$[0].zip").value("97451"))
                .andExpect(jsonPath("$[0].phone").value("841-874-6512"))
                .andExpect(jsonPath("$[0].email").value("jaboyd@email.com"));
    }

    @Test
    void getFirestations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].station").value("3"));
    }

    @Test
    void getMedicalrecords() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].birthdate").value("03/06/1984"))
                .andExpect(jsonPath("$[0].medications[0]").value("aznol:350mg"))
                .andExpect(jsonPath("$[0].medications[1]").value("hydrapermazol:100mg"))
                .andExpect(jsonPath("$[0].allergies[0]").value("nillacilan"));
    }

    @Test
    void getFireStation() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons").isArray())
                .andExpect(jsonPath("$.persons[0].firstName").value("Peter"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Duncan"))
                .andExpect(jsonPath("$.persons[0].address").value("644 Gershwin Cir"))
                .andExpect(jsonPath("$.persons[0].phone").value("841-874-6512"))
                .andExpect(jsonPath("$.adultCount").value(5))
                .andExpect(jsonPath("$.childCount").value(1));
    }

    @Test
    void getChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.children").isArray())
                .andExpect(jsonPath("$.children[0].firstName").value("Tenley"))
                .andExpect(jsonPath("$.children[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$.children[0].age").value(14));
    }

    @Test
    void getPhoneAlert() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("841-874-6512"));
    }

    @Test
    void getFire() throws Exception {
        mockMvc.perform(get("/fire?address=1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons").isArray())
                .andExpect(jsonPath("$.stationNumber").value("3"))
                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
                .andExpect(jsonPath("$.persons[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$.persons[0].age").value(42));
    }

    @Test
    void getFlood() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.households").isArray())
                .andExpect(jsonPath("$.households[0].address").value("644 Gershwin Cir"))
                .andExpect(jsonPath("$.households[0].persons").isArray())
                .andExpect(jsonPath("$.households[0].persons[0].firstName").value("Peter"))
                .andExpect(jsonPath("$.households[0].persons[0].lastName").value("Duncan"));
    }

    @Test
    void getPersonInfo() throws Exception {
        mockMvc.perform(get("/personInfo?lastName=Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].age").value(42))
                .andExpect(jsonPath("$[0].email").value("jaboyd@email.com"))
                .andExpect(jsonPath("$[0].medications[0]").value("aznol:350mg"))
                .andExpect(jsonPath("$[0].medications[1]").value("hydrapermazol:100mg"))
                .andExpect(jsonPath("$[0].allergies[0]").value("nillacilan"));
    }

    @Test
    void getCommunityEmail() throws Exception {
        ClassPathResource resource =
                new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        long expectedEmailCount = data.getPersons().stream()
                .filter(person -> person.getCity().equals("Culver"))
                .count();

        mockMvc.perform(get("/communityEmail?city=Culver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value((int) expectedEmailCount))
                .andExpect(jsonPath("$[0]").value("jaboyd@email.com"))
                .andExpect(jsonPath("$[1]").value("drk@email.com"))
                .andExpect(jsonPath("$[2]").value("tenz@email.com"))
                .andExpect(jsonPath("$[3]").value("jaboyd@email.com"));
    }

    @Test
    void addPerson() throws Exception {
        mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "Test",
                            "lastName": "Person",
                            "address": "1 Test Street",
                            "city": "Test City",
                            "zip": "12345",
                            "phone": "0102030405",
                            "email": "test@example.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Person"));

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.firstName == 'Test' && @.lastName == 'Person')]").isNotEmpty());
    }

    @Test
    void updatePerson() throws Exception {
        mockMvc.perform(put("/person")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "John",
                            "lastName": "Boyd",
                            "address": "2 Test Street",
                            "city": "New Test City",
                            "zip": "54321",
                            "phone": "0607080910",
                            "email": "updated@example.com"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.address").value("2 Test Street"))
                .andExpect(jsonPath("$.city").value("New Test City"))
                .andExpect(jsonPath("$.zip").value("54321"))
                .andExpect(jsonPath("$.phone").value("0607080910"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].address").value("2 Test Street"))
                .andExpect(jsonPath("$[0].city").value("New Test City"))
                .andExpect(jsonPath("$[0].zip").value("54321"))
                .andExpect(jsonPath("$[0].phone").value("0607080910"))
                .andExpect(jsonPath("$[0].email").value("updated@example.com"));
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(post("/person")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "Test",
                            "lastName": "Person",
                            "address": "1 Test Street",
                            "city": "Test City",
                            "zip": "12345",
                            "phone": "0102030405",
                            "email": "test@example.com"
                        }
                        """))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/person")
                        .param("firstName", "Test")
                        .param("lastName", "Person"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.firstName == 'Test' && @.lastName == 'Person')]")
                        .isEmpty());
    }

    @Test
    void addMedicalRecord() throws Exception {
        mockMvc.perform(post("/medicalRecord")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "Test",
                            "lastName": "Medical",
                            "birthdate": "01/01/2000",
                            "medications": ["medication:test"],
                            "allergies": ["allergy:test"]
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Medical"))
                .andExpect(jsonPath("$.birthdate").value("01/01/2000"))
                .andExpect(jsonPath("$.medications[0]").value("medication:test"))
                .andExpect(jsonPath("$.allergies[0]").value("allergy:test"));

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.firstName == 'Test' && @.lastName == 'Medical')]").isNotEmpty());
    }

    @Test
    void updateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "John",
                            "lastName": "Boyd",
                            "birthdate": "02/02/2000",
                            "medications": ["medication:updated"],
                            "allergies": ["allergy:updated"]
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Boyd"))
                .andExpect(jsonPath("$.birthdate").value("02/02/2000"))
                .andExpect(jsonPath("$.medications[0]").value("medication:updated"))
                .andExpect(jsonPath("$.allergies[0]").value("allergy:updated"));

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Boyd"))
                .andExpect(jsonPath("$[0].birthdate").value("02/02/2000"))
                .andExpect(jsonPath("$[0].medications[0]").value("medication:updated"))
                .andExpect(jsonPath("$[0].allergies[0]").value("allergy:updated"));
    }

    @Test
    void deleteMedicalRecord() throws Exception {
        mockMvc.perform(post("/medicalRecord")
                        .contentType("application/json")
                        .content("""
                        {
                            "firstName": "Test",
                            "lastName": "Medical",
                            "birthdate": "01/01/2000",
                            "medications": ["medication:test"],
                            "allergies": ["allergy:test"]
                        }
                        """))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/medicalRecord")
                        .param("firstName", "Test")
                        .param("lastName", "Medical"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.firstName == 'Test' && @.lastName == 'Medical')]")
                        .isEmpty());
    }

    @Test
    void addFireStation() throws Exception {
        mockMvc.perform(post("/firestation")
                        .contentType("application/json")
                        .content("""
                        {
                            "address": "1 Test Station",
                            "station": "99"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("1 Test Station"))
                .andExpect(jsonPath("$.station").value("99"));

        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.address == '1 Test Station' && @.station == '99')]").isNotEmpty());
    }

    @Test
    void updateFireStation() throws Exception {
        mockMvc.perform(put("/firestation")
                        .contentType("application/json")
                        .content("""
                        {
                            "address": "1509 Culver St",
                            "station": "99"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("1509 Culver St"))
                .andExpect(jsonPath("$.station").value("99"));

        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("1509 Culver St"))
                .andExpect(jsonPath("$[0].station").value("99"));
    }

    @Test
    void deleteFireStation() throws Exception {
        mockMvc.perform(post("/firestation")
                        .contentType("application/json")
                        .content("""
                        {
                            "address": "1 Test Station",
                            "station": "99"
                        }
                        """))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/firestation")
                        .param("address", "1 Test Station"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.address == '1 Test Station')]")
                        .isEmpty());
    }

    @Test
    void getAgeWithInvalidBirthdate() {
        AgeService ageService = new AgeServiceImpl();

        assertThrows(
                RuntimeException.class,
                () -> ageService.getAge("date-invalide")
        );
    }
}