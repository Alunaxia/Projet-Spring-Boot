package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.service.CommunityEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {

    private static final Logger logger =
            LoggerFactory.getLogger(CommunityEmailController.class);

    private final CommunityEmailService communityEmailService;

    public CommunityEmailController(
            CommunityEmailService communityEmailService) {

        this.communityEmailService = communityEmailService;
    }

    @GetMapping("/communityEmail")
    public List<String> getCommunityEmail(
            @RequestParam String city) {

        logger.info("GET /communityEmail?city={}", city);

        List<String> emails =
                communityEmailService.getEmailsByCity(city);

        logger.info(
                "GET /communityEmail - ville {} : {} email(s) retourné(s)",
                city,
                emails.size()
        );

        return emails;
    }
}