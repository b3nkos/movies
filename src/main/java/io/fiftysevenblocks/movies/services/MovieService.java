package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.mappers.MovieMapper;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public Movie save(CreateMovieRequest createMovieRequest) {
        Movie movie = movieMapper.fromMovieRequestToMovie(createMovieRequest);
        return movieRepository.save(movie);
    }
}
