package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.dto.FloodDto;

import java.util.List;

public interface FloodService {

    FloodDto getFloodResponse(List<String> stations);
}