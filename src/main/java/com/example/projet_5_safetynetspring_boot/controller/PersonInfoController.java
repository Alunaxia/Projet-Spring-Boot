package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.dto.PersonInfoDto;
import com.example.projet_5_safetynetspring_boot.service.PersonInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Personnalisation pour le swagger
@Tag(
        name = "Person Info",
        description = "Informations détaillées sur les personnes"
)

@RestController
public class PersonInfoController {

    private static final Logger logger =
            LoggerFactory.getLogger(PersonInfoController.class);

    private final PersonInfoService personInfoService;

    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    @GetMapping("/personInfo")
    public List<PersonInfoDto> getPersonInfo(
            @RequestParam String lastName) {

        logger.info("GET /personInfo?lastName={}", lastName);

        List<PersonInfoDto> responses =
                personInfoService.getPersonInfo(lastName);

        logger.info(
                "GET /personInfo - nom {} : {} personne(s) retournée(s)",
                lastName,
                responses.size()
        );

        return responses;
    }
}