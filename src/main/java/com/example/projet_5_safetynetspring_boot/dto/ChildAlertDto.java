package com.example.projet_5_safetynetspring_boot.dto;

import java.util.List;

public class ChildAlertDto {
    private List<ChildAlertPersonDto> children;

    public List<ChildAlertPersonDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildAlertPersonDto> children) {
        this.children = children;
    }
}
