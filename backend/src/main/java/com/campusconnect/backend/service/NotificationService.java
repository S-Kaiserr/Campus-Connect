package com.campusconnect.backend.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.campusconnect.backend.model.Notification;
import com.campusconnect.backend.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(UUID userId, String type, String message) {
        Notification notif = new Notification();
        notif.setUserId(userId);
        notif.setType(type);
        notif.setMessage(message);
        notif.setReadStatus(false);
        notif.setTimestamp(Instant.now());
        return notificationRepository.save(notif);
    }

    public List<Notification> getNotificationsForUser(UUID userId) {
        return notificationRepository.findByUserIdOrderByTimestampDesc(userId);
    }

    public List<Notification> getUnreadNotificationsForUser(UUID userId) {
        return notificationRepository
                .findByUserIdAndReadStatusFalseOrderByTimestampDesc(userId);
    }

    public void markAsRead(UUID notifId) {
        Notification notif = notificationRepository.findById(notifId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notif.setReadStatus(true);
        notificationRepository.save(notif);
    }
}
