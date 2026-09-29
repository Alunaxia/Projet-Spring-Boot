package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class FloodHouseholdDto {

    private String address;
    private List<FloodPersonDto> persons;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodPersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<FloodPersonDto> persons) {
        this.persons = persons;
    }
}