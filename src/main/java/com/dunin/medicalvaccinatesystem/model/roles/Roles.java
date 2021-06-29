package com.dunin.medicalvaccinatesystem.model.roles;

public enum Roles {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    public final String label;

    Roles(String label) {
        this.label = label;
    }
}
