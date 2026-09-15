package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.ChildAlertResponse;
import com.example.projet_5_safetynetspring_boot.service.ChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildAlertController {

    private static final Logger logger =
            LoggerFactory.getLogger(ChildAlertController.class);

    private final ChildAlertService childAlertService;

    public ChildAlertController(ChildAlertService childAlertService) {
        this.childAlertService = childAlertService;
    }

    @GetMapping("/childAlert")
    public ChildAlertResponse getChildAlert(
            @RequestParam String address) {

        logger.info("GET /childAlert?address={}", address);

        ChildAlertResponse response =
                childAlertService.getChildAlertResponse(address);

        logger.info(
                "GET /childAlert - adresse {} : {} enfant(s) retourné(s)",
                address,
                response.getChildren().size()
        );

        return response;
    }
}