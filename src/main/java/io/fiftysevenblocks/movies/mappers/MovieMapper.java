package io.fiftysevenblocks.movies.mappers;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.models.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie fromMovieRequestToMovie(CreateMovieRequest createMovieRequest) {
        return new Movie(createMovieRequest.title(), createMovieRequest.description(),
                createMovieRequest.releaseDate(), createMovieRequest.genre(), createMovieRequest.isPrivate());
    }

    public MovieResponse fromMovieToMovieResponse(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDescription(),
                movie.getReleaseDate(), movie.getGenre(), movie.isPrivate());
    }
}
