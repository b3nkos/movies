package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.exceptions.*;
import io.fiftysevenblocks.movies.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        FieldError fieldError = (FieldError) exception.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .orElseThrow();

        return ResponseEntity.badRequest().body(
                buildErrorResponse(fieldError.getDefaultMessage())
        );
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException exception) {
        return ResponseEntity.badRequest().body(
                buildErrorResponse(exception.getMessage())
        );
    }

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyRegisterException(
            UserAlreadyRegisterException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleUserBadCredentialsException() {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse("Invalid username or password"));
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthenticatedException(UnauthenticatedException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(NotFoundMovieException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundMovieException(NotFoundMovieException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(InvalidMovieUpdateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMovieUpdateException(InvalidMovieUpdateException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(exception.getMessage()));
    }

    private ErrorResponse buildErrorResponse(String message) {

        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message
        );
    }
}
