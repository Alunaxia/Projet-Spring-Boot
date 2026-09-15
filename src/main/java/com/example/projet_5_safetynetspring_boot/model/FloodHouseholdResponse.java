package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class FloodHouseholdResponse {

    private String address;
    private List<FloodPersonResponse> persons;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonResponse> getPersons() {
        return persons;
    }

    public void setPersons(List<FloodPersonResponse> persons) {
        this.persons = persons;
    }
}