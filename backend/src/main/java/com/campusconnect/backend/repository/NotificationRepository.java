package com.campusconnect.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserIdOrderByTimestampDesc(UUID userId);

    List<Notification> findByUserIdAndReadStatusFalseOrderByTimestampDesc(UUID userId);
}
