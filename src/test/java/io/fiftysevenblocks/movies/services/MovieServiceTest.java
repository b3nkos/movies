package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.exceptions.UserNotFoundException;
import io.fiftysevenblocks.movies.mappers.MovieMapper;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.repositories.MovieRepository;
import io.fiftysevenblocks.movies.services.factories.MovieFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = {MovieService.class, MovieMapper.class})
class MovieServiceTest {

    @MockBean
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @Test
    @DisplayName("Should create a new movie")
    public void shouldCreateANewMovie() throws UserNotFoundException {
        CreateMovieRequest movieRequest = MovieFactory.createMovieRequest();
        Movie movie = MovieFactory.movieModel();
        String email = "example@example.com";

        when(movieRepository.save(any(Movie.class)))
                .thenReturn(movie);

        MovieResponse movieResponse = movieService.save(movieRequest, email);

        assertThat(movieResponse).isNotNull();
    }
}