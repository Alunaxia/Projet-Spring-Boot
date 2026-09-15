package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.FireResponse;

public interface FireService {

    FireResponse getFireResponse(String address);
}