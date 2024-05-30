package io.fiftysevenblocks.movies.dtos;

public record UserLoginResponse(String token, long expiresIn) {
}
