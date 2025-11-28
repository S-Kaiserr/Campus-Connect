package com.campusconnect.backend.dto;

import java.util.UUID;

public class AuthResponse {
    private UUID userId;
    private String name;
    private String email;

    public AuthResponse(UUID userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
