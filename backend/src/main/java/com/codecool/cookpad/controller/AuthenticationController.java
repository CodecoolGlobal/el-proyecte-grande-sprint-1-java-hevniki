package com.codecool.cookpad.controller;

import com.codecool.cookpad.security.AuthenticationRequest;
import com.codecool.cookpad.security.AuthenticationResponse;
import com.codecool.cookpad.security.AuthenticationService;
import com.codecool.cookpad.exception.InvalidPasswordException;
import com.codecool.cookpad.security.RegisterRequest;
import com.codecool.cookpad.exception.UsernameAlreadyTakenException;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (UsernameAlreadyTakenException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(
                    AuthenticationResponse.builder().error(e.getMessage()).build()
            );
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
