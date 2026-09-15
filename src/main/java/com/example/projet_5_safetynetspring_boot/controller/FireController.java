package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.FireResponse;
import com.example.projet_5_safetynetspring_boot.service.FireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {

    private static final Logger logger =
            LoggerFactory.getLogger(FireController.class);

    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("/fire")
    public FireResponse getFire(
            @RequestParam String address) {

        logger.info("GET /fire?address={}", address);

        FireResponse response =
                fireService.getFireResponse(address);

        logger.info(
                "GET /fire - adresse {} : {} personne(s) retournée(s), caserne {}",
                address,
                response.getPersons().size(),
                response.getStationNumber()
        );

        return response;
    }
}