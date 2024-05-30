package io.fiftysevenblocks.movies.exceptions;

public class UserAlreadyRegisterException extends Exception {
    public UserAlreadyRegisterException(String message) {
        super(message);
    }
}
