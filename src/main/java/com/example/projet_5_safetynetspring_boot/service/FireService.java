package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.dto.FireDto;

public interface FireService {

    FireDto getFireResponse(String address);
}