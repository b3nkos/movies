package io.fiftysevenblocks.movies.dtos;

import java.time.LocalDate;

public record CreateMovieRequest(String title, String description,
                                 LocalDate releaseDate, String genre, boolean isPrivate) {

    public CreateMovieRequest(String title, String description, LocalDate releaseDate, String genre) {
        this(title, description, releaseDate, genre, false);
    }
}
