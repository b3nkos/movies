package io.fiftysevenblocks.movies.dtos;

import jakarta.validation.constraints.*;

public record UserRegisterRequest(
        @NotBlank(
                message = "Name must not be null or empty"
        )
        String name,
        @NotBlank(
                message = "Last name must not be null or empty"
        )
        String lastname,
        @NotBlank(
                message = "Password must not be null or empty"
        )
        @Size(
                min = 10,
                message = "Password must be at least 10 characters long"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#?])[a-zA-Z!@#?]+$",
                message = "Password must contain at least one lowercase letter, one uppercase letter, and one of the following characters: !, @, #, ? or ]"
        )
        String password,
        @NotBlank(
                message = "Email must not be null or empty"
        )
        @Email(
                message = "Email must be a well-formed email address"
        )
        String email,
        @NotBlank(
                message = "Phone must not be null or empty"
        )
        String phone
) {
}
