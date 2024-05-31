package io.fiftysevenblocks.movies.services.factories;

import io.fiftysevenblocks.movies.dtos.UserResponse;

public class UserFactory {
    public static UserResponse userResponse() {
        return new UserResponse(123L, "John", "Doe",
                "example@example.com", "27636363");
    }
}
