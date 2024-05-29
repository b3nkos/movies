package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        FieldError fieldError = (FieldError) ex.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .orElseThrow();

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
