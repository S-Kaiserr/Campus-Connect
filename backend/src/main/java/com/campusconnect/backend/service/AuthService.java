package com.campusconnect.backend.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.AuthResponse;
import com.campusconnect.backend.dto.LoginRequest;
import com.campusconnect.backend.dto.RegisterRequest;
import com.campusconnect.backend.model.User;
import com.campusconnect.backend.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setMajor(request.getMajor());
        user.setBio(request.getBio());
        user.setCreatedAt(Instant.now());

        User saved = userRepository.save(user);

        return new AuthResponse(saved.getUserId(), saved.getName(), saved.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return new AuthResponse(user.getUserId(), user.getName(), user.getEmail());
    }
}