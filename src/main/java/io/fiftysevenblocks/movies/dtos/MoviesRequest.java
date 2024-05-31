package io.fiftysevenblocks.movies.dtos;

public record MoviesRequest(Long userId, boolean isPublic, int page, int size) {
}
