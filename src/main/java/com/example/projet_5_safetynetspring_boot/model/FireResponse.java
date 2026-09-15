package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class FireResponse {

    private List<FirePersonResponse> persons;
    private String stationNumber;

    public List<FirePersonResponse> getPersons() {
        return persons;
    }

    public void setPersons(List<FirePersonResponse> persons) {
        this.persons = persons;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }
}