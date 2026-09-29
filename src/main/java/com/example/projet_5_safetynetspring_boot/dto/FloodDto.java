package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class FloodDto {

    private List<FloodHouseholdDto> households;

    public List<FloodHouseholdDto> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<FloodHouseholdDto> households) {
        this.households = households;
    }
}