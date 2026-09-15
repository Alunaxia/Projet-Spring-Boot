package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.FireStation;
import com.example.projet_5_safetynetspring_boot.model.Person;
import com.example.projet_5_safetynetspring_boot.model.FireStationResponse;

import java.util.List;

public interface FireStationService {

    List<FireStation> getFirestations();

    List<String> getAddressesByStation(String stationNumber);

    List<Person> getPersonsByAddresses(List<String> addresses);

    FireStationResponse getFireStationResponse(String stationNumber);

    FireStation addFireStation(FireStation fireStation);

    FireStation updateFireStation(FireStation fireStation);

    void deleteFireStation(String address);
}