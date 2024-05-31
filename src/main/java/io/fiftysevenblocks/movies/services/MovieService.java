package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.dtos.*;
import io.fiftysevenblocks.movies.exceptions.NotFoundMovieException;
import io.fiftysevenblocks.movies.exceptions.UserNotFoundException;
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
    private final UserService userService;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper, UserService userService) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.userService = userService;
    }

    public MovieResponse save(CreateMovieRequest createMovieRequest, String userEmail) throws UserNotFoundException {
        UserResponse user = userService.findByEmail(userEmail);
        Movie movie = movieMapper.fromMovieRequestToMovie(createMovieRequest, user);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.fromMovieToMovieResponse(savedMovie);
    }

    public MovieResponse update(Long movieId, String email, UpdateMovieRequest updateMovieRequest)
            throws NotFoundMovieException, UserNotFoundException {
        UserResponse user = userService.findByEmail(email);
        Movie movie = movieRepository.findByIdAndUserId(movieId, user.id())
                .orElseThrow(() -> new NotFoundMovieException("Movie not found or cannot update it"));

        Movie updatedMovie = movieMapper.fromMovieUpdateRequestToMovie(updateMovieRequest, movie);
        Movie savedMovie = movieRepository.save(updatedMovie);
        return movieMapper.fromMovieToMovieResponse(savedMovie);
    }

    public Page<MovieResponse> getAllMovies(MoviesRequest moviesRequest) {
        Pageable pageable = PageRequest.of(moviesRequest.page(), moviesRequest.size());

        Page<Movie> moviePage = movieRepository.getAllByUserIdOrIsPublic(moviesRequest.userId(),
                moviesRequest.isPublic(), pageable);

        return movieMapper.fromMoviePageToMovieResponsePage(moviePage);
    }
}
