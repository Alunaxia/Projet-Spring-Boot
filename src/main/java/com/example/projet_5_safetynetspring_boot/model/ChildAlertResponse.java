package com.example.projet_5_safetynetspring_boot.model;

import java.util.List;

public class ChildAlertResponse {
    private List<ChildAlertPersonResponse> children;

    public List<ChildAlertPersonResponse> getChildren() {
        return children;
    }

    public void setChildren(List<ChildAlertPersonResponse> children) {
        this.children = children;
    }
}
