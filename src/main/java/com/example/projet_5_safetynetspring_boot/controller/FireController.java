package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.dto.FireDto;
import com.example.projet_5_safetynetspring_boot.service.FireService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Personnalisation pour le swagger
@Tag(
        name = "Fire",
        description = "Informations sur les personnes présentes à une adresse"
)

@RestController
public class FireController {

    private static final Logger logger =
            LoggerFactory.getLogger(FireController.class);

    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("/fire")
    public FireDto getFire(
            @RequestParam String address) {

        logger.info("GET /fire?address={}", address);

        FireDto response =
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