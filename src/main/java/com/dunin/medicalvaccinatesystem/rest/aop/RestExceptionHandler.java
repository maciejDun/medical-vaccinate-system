package com.dunin.medicalvaccinatesystem.rest.aop;

import com.dunin.medicalvaccinatesystem.common.exception.TermNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TermNotFoundException.class)
    ResponseEntity<Problem> handleNotFound(TermNotFoundException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Problem> handleException(Exception exception, HttpStatus httpStatus) {
        log.error(exception.getMessage(), exception);

        Problem problem = Problem.builder()
                .status(httpStatus.value())
                .title(httpStatus.getReasonPhrase())
                .detail(exception.getMessage())
                .build();

        return ResponseEntity.status(httpStatus)
                             .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                             .body(problem);
    }
}
