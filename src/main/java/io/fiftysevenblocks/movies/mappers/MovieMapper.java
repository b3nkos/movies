package io.fiftysevenblocks.movies.mappers;

import io.fiftysevenblocks.movies.dtos.CreateMovieRequest;
import io.fiftysevenblocks.movies.dtos.MovieResponse;
import io.fiftysevenblocks.movies.models.Movie;
import io.fiftysevenblocks.movies.models.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie fromMovieRequestToMovie(CreateMovieRequest createMovieRequest) {
        User user = new User();
        user.setId(1L);
        return new Movie(createMovieRequest.title(),
                createMovieRequest.description(),
                createMovieRequest.releaseDate(),
                createMovieRequest.genre(),
                createMovieRequest.isPrivate(),
                user
        );
    }

    public MovieResponse fromMovieToMovieResponse(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDescription(),
                movie.getReleaseDate(), movie.getGenre(), movie.isPublic());
    }

    public Page<MovieResponse> fromMoviePageToMovieResponsePage(Page<Movie> movies) {
        return movies.map(this::fromMovieToMovieResponse);
    }
}
