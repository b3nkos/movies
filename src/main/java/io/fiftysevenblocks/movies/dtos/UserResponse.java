package io.fiftysevenblocks.movies.dtos;

public record UserResponse(Long id, String name, String lastname,
                           String email, String phone) {
}
