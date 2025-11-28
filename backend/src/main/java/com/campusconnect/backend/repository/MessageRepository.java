package com.campusconnect.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    // For DM
    List<Message> findBySenderIdAndReceiverIdOrderByTimestampAsc(UUID senderId, UUID receiverId);

    // For group messages
    List<Message> findByGroupIdOrderByTimestampAsc(UUID groupId);
}