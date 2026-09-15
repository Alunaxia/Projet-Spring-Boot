package com.example.projet_5_safetynetspring_boot.repository;

import com.example.projet_5_safetynetspring_boot.model.Data;
import com.example.projet_5_safetynetspring_boot.model.FireStation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Repository
public class FireStationRepository {

    private final ObjectMapper objectMapper;

    public FireStationRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<FireStation> getFirestations() throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        return data.getFirestations();
    }

    public FireStation addFireStation(FireStation fireStation) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getFirestations().add(fireStation);

        objectMapper.writeValue(resource.getFile(), data);

        return fireStation;
    }

    public FireStation updateFireStation(FireStation updatedFireStation) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        for (FireStation fireStation : data.getFirestations()) {
            if (fireStation.getAddress().equals(updatedFireStation.getAddress())) {

                fireStation.setStation(updatedFireStation.getStation());

                objectMapper.writeValue(resource.getFile(), data);

                return fireStation;
            }
        }

        return null;
    }

    public void deleteFireStation(String address) throws IOException {
        ClassPathResource resource = new ClassPathResource("donnees.json");

        Data data = objectMapper.readValue(
                resource.getInputStream(),
                Data.class
        );

        data.getFirestations().removeIf(
                fireStation -> fireStation.getAddress().equals(address)
        );

        objectMapper.writeValue(resource.getFile(), data);
    }
}