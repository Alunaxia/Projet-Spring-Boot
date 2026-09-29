package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.dto.ChildAlertDto;
import com.example.projet_5_safetynetspring_boot.service.ChildAlertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Personnalisation pour le swagger
@Tag(
        name = "Child Alert",
        description = "Recherche des enfants à une adresse"
)

@RestController
public class ChildAlertController {

    private static final Logger logger =
            LoggerFactory.getLogger(ChildAlertController.class);

    private final ChildAlertService childAlertService;

    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping("/childAlert")
    public ChildAlertDto getChildAlert(
            @RequestParam String address) {

        logger.info("GET /childAlert?address={}", address);

        ChildAlertDto response =
                childAlertService.getChildAlertResponse(address);

        logger.info(
                "GET /childAlert - adresse {} : {} enfant(s) retourné(s)",
                address,
                response.getChildren().size()
        );

        return response;
    }
}