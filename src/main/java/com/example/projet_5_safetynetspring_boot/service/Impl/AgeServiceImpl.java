package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.service.AgeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class AgeServiceImpl implements AgeService {

    @Override
    public int getAge(String birthdate) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate birthDate = LocalDate.parse(birthdate, formatter);

        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}