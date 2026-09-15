package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.PersonInfoResponse;
import com.example.projet_5_safetynetspring_boot.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {

    private static final Logger logger =
            LoggerFactory.getLogger(PersonInfoController.class);

    private final PersonInfoService personInfoService;

    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    @GetMapping("/personInfo")
    public List<PersonInfoResponse> getPersonInfo(
            @RequestParam String lastName) {

        logger.info("GET /personInfo?lastName={}", lastName);

        List<PersonInfoResponse> responses =
                personInfoService.getPersonInfo(lastName);

        logger.info(
                "GET /personInfo - nom {} : {} personne(s) retournée(s)",
                lastName,
                responses.size()
        );

        return responses;
    }
}