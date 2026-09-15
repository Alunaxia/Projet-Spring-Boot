package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.FireStationResponse;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

    private static final Logger logger =
            LoggerFactory.getLogger(FireStationController.class);

    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<FireStation> getFirestations() {
        logger.info("GET /firestation");

        List<FireStation> firestations = fireStationService.getFirestations();

        logger.info("GET /firestation - {} casernes retournées", firestations.size());

        return firestations;
    }

    @GetMapping(value = "/firestation", params = "stationNumber")
    public FireStationResponse getFireStationPerNumber(
            @RequestParam String stationNumber) {

        logger.info("GET /firestation?stationNumber={}", stationNumber);

        FireStationResponse response =
                fireStationService.getFireStationResponse(stationNumber);

        logger.info(
                "GET /firestation - caserne {} : {} personnes retournées ({} adultes, {} enfants)",
                stationNumber,
                response.getPersons().size(),
                response.getAdultCount(),
                response.getChildCount()
        );

        return response;
    }

    @PostMapping("/firestation")
    public FireStation addFireStation(
            @RequestBody FireStation fireStation) {

        logger.info(
                "POST /firestation - adresse {}, caserne {}",
                fireStation.getAddress(),
                fireStation.getStation()
        );

        FireStation addedFireStation =
                fireStationService.addFireStation(fireStation);

        logger.info(
                "POST /firestation - caserne ajoutée pour l'adresse {}",
                fireStation.getAddress()
        );

        return addedFireStation;
    }

    @PutMapping("/firestation")
    public FireStation updateFireStation(
            @RequestBody FireStation fireStation) {

        logger.info(
                "PUT /firestation - adresse {}, nouvelle caserne {}",
                fireStation.getAddress(),
                fireStation.getStation()
        );

        FireStation updatedFireStation =
                fireStationService.updateFireStation(fireStation);

        logger.info(
                "PUT /firestation - caserne de l'adresse {} modifiée",
                fireStation.getAddress()
        );

        return updatedFireStation;
    }

    @DeleteMapping("/firestation")
    public void deleteFireStation(
            @RequestParam String address) {

        logger.info(
                "DELETE /firestation?address={}",
                address
        );

        fireStationService.deleteFireStation(address);

        logger.info(
                "DELETE /firestation - caserne de l'adresse {} supprimée",
                address
        );
    }
}