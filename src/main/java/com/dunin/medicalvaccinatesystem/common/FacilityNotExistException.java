package com.dunin.medicalvaccinatesystem.common;

public class FacilityNotExistException extends RuntimeException {
    public FacilityNotExistException(String message) {
        super(message);
    }
}
