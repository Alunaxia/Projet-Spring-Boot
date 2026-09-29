package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class ChildAlertPersonDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<ChildAlertHouseholdMemberDto> householdMembers;

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

    public List<ChildAlertHouseholdMemberDto> getHouseholdMembers() {
        return householdMembers;
    }

    public void setHouseholdMembers(List<ChildAlertHouseholdMemberDto> householdMembers) {
        this.householdMembers = householdMembers;
    }
}
