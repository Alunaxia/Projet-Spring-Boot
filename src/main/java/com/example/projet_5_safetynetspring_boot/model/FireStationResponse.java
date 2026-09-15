package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class FireStationResponse {

    private int adultCount;
    private int childCount;
    private List<FireStationPersonResponse> persons;

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public List<FireStationPersonResponse> getPersons() {
        return persons;
    }

    public void setPersons(List<FireStationPersonResponse> persons) {
        this.persons = persons;
    }
}