package io.fiftysevenblocks.movies.dtos;

public record ErrorResponse(int httpCode, String httpCodeMessage, String message) {
}
