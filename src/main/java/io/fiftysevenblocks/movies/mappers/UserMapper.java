package io.fiftysevenblocks.movies.mappers;

import io.fiftysevenblocks.movies.dtos.UserRegisterRequest;
import io.fiftysevenblocks.movies.dtos.UserResponse;
import io.fiftysevenblocks.movies.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User fromUserRegisterRequestToUserModel(UserRegisterRequest userRegisterRequest) {
        return new User(
                userRegisterRequest.name(),
                userRegisterRequest.lastname(),
                passwordEncoder.encode(userRegisterRequest.password()),
                userRegisterRequest.email(),
                userRegisterRequest.phone()
        );
    }

    public UserResponse fromUserRegisterRequestToResponse(UserRegisterRequest userRegisterRequest) {
        return new UserResponse(userRegisterRequest.name(),
                userRegisterRequest.lastname(), userRegisterRequest.email(), userRegisterRequest.phone());
    }

    public UserResponse fromUserModelToUserResponse(User user) {
        return new UserResponse(user.getName(), user.getLastname(), user.getEmail(), user.getPhone());
    }
}
