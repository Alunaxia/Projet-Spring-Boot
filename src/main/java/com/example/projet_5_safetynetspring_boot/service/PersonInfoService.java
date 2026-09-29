package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.dto.PersonInfoDto;

import java.util.List;

public interface PersonInfoService {

    List<PersonInfoDto> getPersonInfo(String lastName);
}