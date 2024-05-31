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
                buildErrorResponse(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage())
        );
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoginException(InvalidLoginException exception) {
        return ResponseEntity.badRequest().body(
                buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage())
        );
    }

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyRegisterException(
            UserAlreadyRegisterException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleUserBadCredentialsException() {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or password"));
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthenticatedException(UnauthenticatedException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(NotFoundMovieException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundMovieException(NotFoundMovieException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(InvalidMovieUpdateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMovieUpdateException(InvalidMovieUpdateException exception) {
        return ResponseEntity.badRequest()
                .body(buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    private ErrorResponse buildErrorResponse(HttpStatus status, String message) {

        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message
        );
    }
}
