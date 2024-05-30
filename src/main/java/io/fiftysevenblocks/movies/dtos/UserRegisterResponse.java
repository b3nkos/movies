package io.fiftysevenblocks.movies.dtos;

public record UserRegisterResponse(String name, String lastname,
                                   String email, String phone) {
}
