package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import com.example.projet_5_safetynetspring_boot.service.PhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImpl implements PhoneAlertService {

    private final FireStationService fireStationService;
    private final PersonService personService;
    private static final Logger logger =
            LoggerFactory.getLogger(PhoneAlertServiceImpl.class);

    public PhoneAlertServiceImpl(
            FireStationService fireStationService,
            PersonService personService) {

        this.fireStationService = fireStationService;
        this.personService = personService;
    }

    @Override
    public List<String> getPhoneNumbersByStation(String stationNumber) {

        logger.debug(
                "Recherche des numéros de téléphone pour la caserne {}",
                stationNumber
        );

        List<String> addresses =
                fireStationService.getAddressesByStation(stationNumber);

        logger.debug(
                "{} adresse(s) trouvée(s) pour la caserne {}",
                addresses.size(),
                stationNumber
        );

        List<String> phoneNumbers = new ArrayList<>();

        for (Person person : personService.getPersons()) {
            if (addresses.contains(person.getAddress())) {
                phoneNumbers.add(person.getPhone());
            }
        }

        logger.debug(
                "{} numéro(s) de téléphone trouvé(s) pour la caserne {}",
                phoneNumbers.size(),
                stationNumber
        );

        return phoneNumbers;
    }
}