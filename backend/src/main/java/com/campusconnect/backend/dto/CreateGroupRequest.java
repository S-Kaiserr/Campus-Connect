package com.campusconnect.backend.dto;

import java.util.UUID;

public class CreateGroupRequest {
    private String name;
    private String subject;
    private String description;
    private UUID createdBy;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
    
}