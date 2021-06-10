package com.dunin.medicalvaccinatesystem.rest.aop;

import com.dunin.medicalvaccinatesystem.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(TermNotFoundException.class)
    ResponseEntity<Problem> handleTermNotFound(TermNotFoundException exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TermAlreadyTakenException.class)
    ResponseEntity<Problem> handleTermAlreadyTaken(TermAlreadyTakenException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TermAlreadyExistsException.class)
    ResponseEntity<Problem> handleTermAlreadyExist(TermAlreadyExistsException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FacilityNotExistException.class)
    ResponseEntity<Problem> handleFacilityNotExist(FacilityNotExistException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FKOfFacilityExistInAnotherTableException.class)
    ResponseEntity<Problem> handleFKOfFacilityExist(FKOfFacilityExistInAnotherTableException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FacilityAlreadyExistException.class)
    ResponseEntity<Problem> handleFacilityAlreadyExist(FacilityAlreadyExistException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    ResponseEntity<Problem> handleAlreadyRegistered(UserAlreadyRegisteredException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Problem> handleUserNotFound(UserNotFoundException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<Problem> handleInappropriateRole(HttpMessageNotReadableException exception) {
        InappropriateDataException inappropriateDataException =
                new InappropriateDataException("Inappropriate format of data");
        return handleException(inappropriateDataException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Problem> handleNotBlankUser(MethodArgumentNotValidException exception) {
        RuntimeException newException = getNewException(exception);
        return handleException(newException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<Problem> handleUserAlreadyExist(UserAlreadyExistsException exception) {
        return handleException(exception, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(VaccinatedUserNotFoundException.class)
    ResponseEntity<Problem> handleUserNotFound(VaccinatedUserNotFoundException exception) {
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

    private String getDefaultMessage(MethodArgumentNotValidException exception) {
        Optional<FieldError> fieldError = Optional.ofNullable(exception.getBindingResult().getFieldError());
        return fieldError.map(DefaultMessageSourceResolvable::getDefaultMessage).orElse(null);
    }

    private RuntimeException getNewException(MethodArgumentNotValidException exception) {
        String message = getDefaultMessage(exception);
        return new RuntimeException(message);
    }
}
