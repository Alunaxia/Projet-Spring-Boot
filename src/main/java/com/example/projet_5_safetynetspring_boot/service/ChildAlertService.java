package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.ChildAlertResponse;

public interface ChildAlertService {

    ChildAlertResponse getChildAlertResponse(String address);
}