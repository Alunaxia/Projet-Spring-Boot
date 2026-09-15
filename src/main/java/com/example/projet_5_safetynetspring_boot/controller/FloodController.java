package com.example.projet_5_safetynetspring_boot.controller;

import com.example.projet_5_safetynetspring_boot.model.FloodResponse;
import com.example.projet_5_safetynetspring_boot.service.FloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FloodController {

    private static final Logger logger =
            LoggerFactory.getLogger(FloodController.class);

    private final FloodService floodService;

    public FloodController(FloodService floodService) {
        this.floodService = floodService;
    }

    @GetMapping("/flood/stations")
    public FloodResponse getFlood(
            @RequestParam String stations) {

        logger.info("GET /flood/stations?stations={}", stations);

        List<String> stationNumbers =
                Arrays.stream(stations.split(","))
                        .map(String::trim)
                        .toList();

        FloodResponse response =
                floodService.getFloodResponse(stationNumbers);

        logger.info(
                "GET /flood/stations - {} foyer(s) retourné(s)",
                response.getHouseholds().size()
        );

        return response;
    }
}