package com.dunin.medicalvaccinatesystem.common;

public class FKOfFacilityExistInAnotherTableException extends RuntimeException {
    public FKOfFacilityExistInAnotherTableException(String message) {
        super(message);
    }
}
