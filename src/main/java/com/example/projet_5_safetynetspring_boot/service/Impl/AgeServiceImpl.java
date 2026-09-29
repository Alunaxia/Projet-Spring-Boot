package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.service.AgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class AgeServiceImpl implements AgeService {

    private static final Logger logger =
            LoggerFactory.getLogger(AgeServiceImpl.class);

    @Override
    public int getAge(String birthdate) {
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MM/dd/yyyy");

            LocalDate birthDate = LocalDate.parse(birthdate, formatter);

            int age = Period.between(birthDate, LocalDate.now()).getYears();

            logger.debug(
                    "Âge calculé : {} ans pour la date de naissance {}",
                    age,
                    birthdate
            );

            return age;

        } catch (Exception e) {
            logger.error(
                    "Erreur lors du calcul de l'âge pour la date de naissance {}",
                    birthdate,
                    e
            );

            throw new RuntimeException(
                    "Impossible de calculer l'âge",
                    e
            );
        }
    }
}