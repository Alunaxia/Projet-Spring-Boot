package com.example.projet_5_safetynetspring_boot.service;

import com.example.projet_5_safetynetspring_boot.model.FloodResponse;

import java.util.List;

public interface FloodService {

    FloodResponse getFloodResponse(List<String> stations);
}