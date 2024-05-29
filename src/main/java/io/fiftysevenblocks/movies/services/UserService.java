package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.dtos.UserLoginRequest;
import io.fiftysevenblocks.movies.dtos.UserRegisterRequest;
import io.fiftysevenblocks.movies.dtos.UserResponse;
import io.fiftysevenblocks.movies.mappers.UserMapper;
import io.fiftysevenblocks.movies.models.User;
import io.fiftysevenblocks.movies.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        User user = userMapper.fromUserRegisterRequestToUserModel(userRegisterRequest);
        return userMapper.fromUserModelToUserResponse(userRepository.save(user));
    }

    public UserResponse login(UserLoginRequest userLoginRequest) throws Exception {

        User user = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> new Exception("Invalid username or password"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        return userMapper.fromUserModelToUserResponse(user);
    }
}
