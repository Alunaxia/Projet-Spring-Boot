package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.PersonInfoResponse;

import java.util.List;

public interface PersonInfoService {

    List<PersonInfoResponse> getPersonInfo(String lastName);
}