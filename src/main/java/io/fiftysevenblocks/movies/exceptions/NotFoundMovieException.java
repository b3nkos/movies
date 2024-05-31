package io.fiftysevenblocks.movies.exceptions;

public class NotFoundMovieException extends Exception {
    public NotFoundMovieException(String message) {
        super(message);
    }
}
