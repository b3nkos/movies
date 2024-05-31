package io.fiftysevenblocks.movies.repositories;

import io.fiftysevenblocks.movies.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> getAllByUserIdOrIsPublic(Long userId, boolean isPublic, Pageable pageable);

    Optional<Movie> findByIdAndUserId(Long id, Long userId);
}
