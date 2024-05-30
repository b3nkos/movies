package io.fiftysevenblocks.movies.services.factories;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.models.User;

import java.time.LocalDate;

public class MovieFactory {
    public static CreateMovieRequest createMovieRequest() {
        return new CreateMovieRequest(
                "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
                LocalDate.parse("2010-07-16"),
                "Science Fiction",
                false,
                1L
        );
    }

    public static Movie movieModel() {
        Movie movie = new Movie(
                "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
                LocalDate.parse("2010-07-16"),
                "Science Fiction",
                false,
                new User()
        );

        movie.setId(1L);

        return movie;
    }
}
