package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.service.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {

    private static final Logger logger =
            LoggerFactory.getLogger(PhoneAlertController.class);

    private final PhoneAlertService phoneAlertService;

    public PhoneAlertController(PhoneAlertService phoneAlertService) {
        this.phoneAlertService = phoneAlertService;
    }

    @GetMapping("/phoneAlert")
    public List<String> getPhoneAlert(
            @RequestParam String firestation) {

        logger.info("GET /phoneAlert?firestation={}", firestation);

        List<String> phoneNumbers =
                phoneAlertService.getPhoneNumbersByStation(firestation);

        logger.info(
                "GET /phoneAlert - caserne {} : {} numéro(s) retourné(s)",
                firestation,
                phoneNumbers.size()
        );

        return phoneNumbers;
    }
}