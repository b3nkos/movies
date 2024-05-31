package io.fiftysevenblocks.movies.mappers;

import io.fiftysevenblocks.movies.dtos.UserLoginResponse;
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

    public UserResponse fromUserModelToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public UserLoginResponse fromUserModelToUserLoginResponse(String token, long expiresIn) {
        return new UserLoginResponse(token, expiresIn);
    }
}
