package com.example.projet_5_safetynetspring_boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SafetyNetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").exists())
                .andExpect(jsonPath("$[0].lastName").exists());
    }

    @Test
    void getFirestations() throws Exception {
        mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].address").exists())
                .andExpect(jsonPath("$[0].station").exists());
    }

    @Test
    void getMedicalrecords() throws Exception {
        mockMvc.perform(get("/medicalrecords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").exists())
                .andExpect(jsonPath("$[0].lastName").exists())
                .andExpect(jsonPath("$[0].birthdate").exists())
                .andExpect(jsonPath("$[0].medications").isArray())
                .andExpect(jsonPath("$[0].allergies").isArray());
    }

    @Test
    void getFireStation() throws Exception {
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.persons").isArray())
                .andExpect(jsonPath("$.adultCount").exists())
                .andExpect(jsonPath("$.childCount").exists())
                .andExpect(jsonPath("$.persons[0].firstName").exists())
                .andExpect(jsonPath("$.persons[0].lastName").exists())
                .andExpect(jsonPath("$.persons[0].address").exists())
                .andExpect(jsonPath("$.persons[0].phone").exists());
    }

    @Test
    void getChildAlert() throws Exception {
        mockMvc.perform(get("/childAlert?address=1509 Culver St"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.children").isArray());
    }
}