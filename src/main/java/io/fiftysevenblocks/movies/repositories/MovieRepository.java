package io.fiftysevenblocks.movies.repositories;

import io.fiftysevenblocks.movies.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> getAllByUserIdOrPublic(Long userId, boolean isPublic, Pageable pageable);
}
