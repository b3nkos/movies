package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.dtos.UserLoginRequest;
import io.fiftysevenblocks.movies.dtos.UserLoginResponse;
import io.fiftysevenblocks.movies.dtos.UserRegisterRequest;
import io.fiftysevenblocks.movies.dtos.UserResponse;
import io.fiftysevenblocks.movies.exceptions.InvalidLoginException;
import io.fiftysevenblocks.movies.exceptions.UserAlreadyRegisterException;
import io.fiftysevenblocks.movies.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest)
            throws UserAlreadyRegisterException {
        UserResponse registered = userService.register(userRegisterRequest);
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest)
            throws InvalidLoginException {
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        return ResponseEntity.ok(userLoginResponse);
    }
}
