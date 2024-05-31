package io.fiftysevenblocks.movies.dtos;

import java.time.LocalDate;

public record UpdateMovieRequest(String title, String description,
                                 LocalDate releaseDate, String genre, boolean isPrivate) {
}
