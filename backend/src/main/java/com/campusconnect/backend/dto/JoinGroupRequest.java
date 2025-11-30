package com.campusconnect.backend.dto;

import java.util.UUID;

public class JoinGroupRequest {
    private UUID userId;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
}