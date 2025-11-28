package com.campusconnect.backend.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notif_id")
    private UUID notifId;

    @Column(length = 50)
    private String type;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "user_id", nullable = false)
    private UUID userId; // FK to users.user_id

    @Column(name = "read_status", nullable = false)
    private boolean readStatus = false;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp = Instant.now();

    public Notification() {
    }

    public UUID getNotifId() {
        return notifId;
    }

    public void setNotifId(UUID notifId) {
        this.notifId = notifId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}