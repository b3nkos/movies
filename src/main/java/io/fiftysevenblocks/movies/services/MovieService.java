package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.dtos.MoviesRequest;
import io.fiftysevenblocks.movies.mappers.MovieMapper;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public MovieResponse save(CreateMovieRequest createMovieRequest) {
        Movie movie = movieMapper.fromMovieRequestToMovie(createMovieRequest);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.fromMovieToMovieResponse(savedMovie);
    }

    public Page<MovieResponse> getAllMovies(MoviesRequest moviesRequest) {
        Pageable pageable = PageRequest.of(moviesRequest.page(), moviesRequest.size());

        Page<Movie> moviePage = movieRepository.getAllByUserIdOrPublic(moviesRequest.userId(),
                moviesRequest.isPrivate(), pageable);

        return movieMapper.fromMoviePageToMovieResponsePage(moviePage);
    }
}
