package io.fiftysevenblocks.movies.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String password,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone
) {
}
