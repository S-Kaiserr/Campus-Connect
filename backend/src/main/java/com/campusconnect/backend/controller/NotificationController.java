package com.campusconnect.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.model.Notification;
import com.campusconnect.backend.service.NotificationService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getNotificationsForUser(userId));
    }

    @GetMapping("/users/{userId}/notifications/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotificationsForUser(userId));
    }

    @PostMapping("/notifications/{notifId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable UUID notifId) {
        notificationService.markAsRead(notifId);
        return ResponseEntity.ok().build();
    }
}