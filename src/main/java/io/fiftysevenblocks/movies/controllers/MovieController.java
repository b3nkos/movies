package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.dtos.MoviesRequest;
import io.fiftysevenblocks.movies.dtos.UpdateMovieRequest;
import io.fiftysevenblocks.movies.exceptions.NotFoundMovieException;
import io.fiftysevenblocks.movies.exceptions.UnauthenticatedException;
import io.fiftysevenblocks.movies.exceptions.UserNotFoundException;
import io.fiftysevenblocks.movies.models.User;
import io.fiftysevenblocks.movies.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieResponse> addMovie(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody CreateMovieRequest createMovieRequest) throws UserNotFoundException {
        MovieResponse movie = movieService.save(createMovieRequest, userDetails.getUsername());
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable Long id,
                                                     @RequestBody UpdateMovieRequest updateMovieRequest)
            throws UserNotFoundException, NotFoundMovieException {
        MovieResponse movie = movieService.update(id, userDetails.getUsername(), updateMovieRequest);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies")
    public ResponseEntity<Page<MovieResponse>> getMovies(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "size", defaultValue = "5") int size)
            throws UnauthenticatedException {
        if (userDetails == null) {
            throw new UnauthenticatedException("User is not authenticated");
        }

        User user = (User) userDetails;
        MoviesRequest moviesRequest = new MoviesRequest(user.getId(), true, page, size);
        Page<MovieResponse> allMovies = movieService.getAllMovies(moviesRequest);
        return ResponseEntity.ok(allMovies);
    }
}
