package com.example.projet_5_safetynetspring_boot.service;

import java.util.List;

public interface PhoneAlertService {

    List<String> getPhoneNumbersByStation(String stationNumber);
}