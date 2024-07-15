package com.codecool.cookpad.security;

import com.codecool.cookpad.exception.InvalidPasswordException;
import com.codecool.cookpad.model.Role;
import com.codecool.cookpad.model.entity.User;
import com.codecool.cookpad.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  com.codecool.cookpad.exception.UsernameAlreadyTakenException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        Optional<User> byUsername = repository.findByUsername(request.getUsername());
        if (byUsername.isPresent()) {
            throw new UsernameAlreadyTakenException("Username is already taken");
        }
        if (request.getPassword().length() < 5) {
            throw new InvalidPasswordException("Password is too short");
        }
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .id(user.getId().toString())
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }
}
