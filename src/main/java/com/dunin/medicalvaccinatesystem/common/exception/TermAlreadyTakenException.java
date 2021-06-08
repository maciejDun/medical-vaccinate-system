package com.dunin.medicalvaccinatesystem.common.exception;

public class TermAlreadyTakenException extends RuntimeException {
    public TermAlreadyTakenException(String message) {
        super(message);
    }
}
