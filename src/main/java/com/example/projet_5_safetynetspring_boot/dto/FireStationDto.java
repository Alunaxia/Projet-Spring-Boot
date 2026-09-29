package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class FireStationDto {

    private int adultCount;
    private int childCount;
    private List<FireStationPersonDto> persons;

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

    public List<FireStationPersonDto> getPersons() {
        return persons;
    }

    public void setPersons(List<FireStationPersonDto> persons) {
        this.persons = persons;
    }
}