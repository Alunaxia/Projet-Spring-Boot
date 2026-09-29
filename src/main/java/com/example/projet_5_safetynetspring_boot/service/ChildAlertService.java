package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.dto.ChildAlertDto;

public interface ChildAlertService {

    ChildAlertDto getChildAlertResponse(String address);
}