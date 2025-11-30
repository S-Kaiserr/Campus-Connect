package com.campusconnect.backend.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.SendMessageRequest;
import com.campusconnect.backend.model.Message;
import com.campusconnect.backend.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final NotificationService notificationService; 

    public MessageService(MessageRepository messageRepository,
                          NotificationService notificationService) {
        this.messageRepository = messageRepository;
        this.notificationService = notificationService;
    }

    public Message sendMessage(SendMessageRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new RuntimeException("Message content cannot be empty");
        }

        Message message = new Message();
        message.setSenderId(request.getSenderId());
        message.setReceiverId(request.getReceiverId());
        message.setGroupId(request.getGroupId());
        message.setContent(request.getContent());
        message.setTimestamp(Instant.now());

        Message saved = messageRepository.save(message);

        if (request.getReceiverId() != null && request.getGroupId() == null) {
            notificationService.createNotification(
                    request.getReceiverId(),
                    "DIRECT_MESSAGE",
                    "You received a new message."
            );
        }
        return saved;
    }


    public List<Message> getDirectMessages(UUID user1, UUID user2) {
        // will fetch in both directions and merge if needed.
        List<Message> messages1 = messageRepository
                .findBySenderIdAndReceiverIdOrderByTimestampAsc(user1, user2);
        List<Message> messages2 = messageRepository
                .findBySenderIdAndReceiverIdOrderByTimestampAsc(user2, user1);

        messages1.addAll(messages2);
        messages1.sort((a, b) -> a.getTimestamp().compareTo(b.getTimestamp()));
        return messages1;
    }

    public List<Message> getGroupMessages(UUID groupId) {
        return messageRepository.findByGroupIdOrderByTimestampAsc(groupId);
    }
}
