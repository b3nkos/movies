package io.fiftysevenblocks.movies.dtos;

public record MoviesRequest(Long userId, boolean isPrivate, int page, int size) {
}
