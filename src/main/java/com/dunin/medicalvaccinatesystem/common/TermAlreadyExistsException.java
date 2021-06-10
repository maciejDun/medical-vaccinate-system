package com.dunin.medicalvaccinatesystem.common;

public class TermAlreadyExistsException extends RuntimeException {
    public TermAlreadyExistsException(String message) {
        super(message);
    }
}
