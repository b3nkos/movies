package io.fiftysevenblocks.movies.controllers;

import io.fiftysevenblocks.movies.dtos.UserRegisterRequest;
import io.fiftysevenblocks.movies.dtos.UserResponse;
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

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        UserResponse registered = userService.register(userRegisterRequest);
        return ResponseEntity.ok(registered);
    }
}
