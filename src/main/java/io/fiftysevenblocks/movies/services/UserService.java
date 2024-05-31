package io.fiftysevenblocks.movies.services;

import io.fiftysevenblocks.movies.exceptions.InvalidLoginException;
import io.fiftysevenblocks.movies.dtos.UserLoginRequest;
import io.fiftysevenblocks.movies.dtos.UserLoginResponse;
import io.fiftysevenblocks.movies.dtos.UserRegisterRequest;
import io.fiftysevenblocks.movies.dtos.UserResponse;
import io.fiftysevenblocks.movies.exceptions.UserAlreadyRegisterException;
import io.fiftysevenblocks.movies.exceptions.UserNotFoundException;
import io.fiftysevenblocks.movies.mappers.UserMapper;
import io.fiftysevenblocks.movies.models.User;
import io.fiftysevenblocks.movies.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    public UserResponse register(UserRegisterRequest userRegisterRequest) throws UserAlreadyRegisterException {

        Optional<User> optionalUser = userRepository.findByEmail(userRegisterRequest.email());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyRegisterException("This email address is already in use");
        }

        User user = userMapper.fromUserRegisterRequestToUserModel(userRegisterRequest);
        return userMapper.fromUserModelToUserResponse(userRepository.save(user));
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) throws InvalidLoginException {

        User user = userRepository.findByEmail(userLoginRequest.email())
                .orElseThrow(() -> new InvalidLoginException("Invalid username or password"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.email(),
                        userLoginRequest.password()
                )
        );

        return userMapper.fromUserModelToUserLoginResponse(jwtService.generateToken(user),
                jwtService.getExpirationTime());
    }

    public UserResponse findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
        return userMapper.fromUserModelToUserResponse(user);
    }
}
