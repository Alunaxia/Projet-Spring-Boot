package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class FloodResponse {

    private List<FloodHouseholdResponse> households;

    public List<FloodHouseholdResponse> getHouseholds() {
        return households;
    }

    public void setHouseholds(List<FloodHouseholdResponse> households) {
        this.households = households;
    }
}