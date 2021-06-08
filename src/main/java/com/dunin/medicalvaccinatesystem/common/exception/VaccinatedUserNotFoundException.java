package com.dunin.medicalvaccinatesystem.common.exception;

public class VaccinatedUserNotFoundException extends RuntimeException {
    public VaccinatedUserNotFoundException(String message) {
        super(message);
    }
}

