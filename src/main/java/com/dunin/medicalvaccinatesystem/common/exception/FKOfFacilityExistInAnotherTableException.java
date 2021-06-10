package com.dunin.medicalvaccinatesystem.common.exception;

public class FKOfFacilityExistInAnotherTableException extends RuntimeException {
    public FKOfFacilityExistInAnotherTableException(String message) {
        super(message);
    }
}
