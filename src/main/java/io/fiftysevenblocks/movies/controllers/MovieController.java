package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.mappers.MovieMapper;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        Movie movie = movieService.save(createMovieRequest);
        MovieResponse movieResponse = movieMapper.fromMovieToMovieResponse(movie);
        return ResponseEntity.ok(movieResponse);
    }
}
