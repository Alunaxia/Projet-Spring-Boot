package com.example.projet_5_safetynetspring_boot.service.Impl;

import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.service.FireStationService;
import com.example.projet_5_safetynetspring_boot.service.PersonService;
import com.example.projet_5_safetynetspring_boot.service.PhoneAlertService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneAlertServiceImpl implements PhoneAlertService {

    private final FireStationService fireStationService;
    private final PersonService personService;

    public PhoneAlertServiceImpl(
            FireStationService fireStationService,
            PersonService personService) {

        this.fireStationService = fireStationService;
        this.personService = personService;
    }

    @Override
    public List<String> getPhoneNumbersByStation(String stationNumber) {

        List<String> addresses =
                fireStationService.getAddressesByStation(stationNumber);

        List<String> phoneNumbers = new ArrayList<>();

        for (Person person : personService.getPersons()) {
            if (addresses.contains(person.getAddress())) {
                phoneNumbers.add(person.getPhone());
            }
        }

        return phoneNumbers;
    }
}