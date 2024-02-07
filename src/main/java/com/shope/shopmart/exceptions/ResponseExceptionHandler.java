package com.shope.shopmart.exceptions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<?> handleConstraintViolation(Exception ex) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        List<String> ValidationMessages = null;
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause)
            .getConstraintViolations();
            ValidationMessages = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(ValidationMessages, HttpStatus.BAD_REQUEST);

    }

    public class StorageException extends RuntimeException {
        public StorageException(String message) {
            super(message);
        }

    }
}