package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class FireDto {

    private List<FirePersonDto> persons;
    private String stationNumber;

    public List<FirePersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<FirePersonDto> persons) {
        this.persons = persons;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
}