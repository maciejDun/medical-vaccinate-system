package com.dunin.medicalvaccinatesystem.common;

public class FacilityAlreadyExistException extends RuntimeException {
    public FacilityAlreadyExistException(String message) {
        super(message);
    }
}
