package io.fiftysevenblocks.movies.dtos;

import java.time.LocalDate;

public record UpdateMovieRequest(Long id, String title, String description,
                                 LocalDate releaseDate, String genre, boolean isPrivate) {
    public UpdateMovieRequest {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
