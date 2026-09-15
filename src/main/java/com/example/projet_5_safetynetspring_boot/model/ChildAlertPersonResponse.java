package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class ChildAlertPersonResponse {
    private String firstName;
    private String lastName;
    private int age;
    private List<ChildAlertHouseholdMemberResponse> householdMembers;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ChildAlertHouseholdMemberResponse> getHouseholdMembers() {
        return householdMembers;
    }

    public void setHouseholdMembers(List<ChildAlertHouseholdMemberResponse> householdMembers) {
        this.householdMembers = householdMembers;
    }
}
