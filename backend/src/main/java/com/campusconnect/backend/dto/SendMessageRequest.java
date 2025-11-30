package com.campusconnect.backend.dto;

import java.util.UUID;

public class SendMessageRequest {
    private UUID senderId;
    private UUID receiverId; // think its nullable for group messages
    private UUID groupId;    // also nullable for direct messages
    private String content;

    public UUID getSenderId() { return senderId; }
    public void setSenderId(UUID senderId) { this.senderId = senderId; }

    public UUID getReceiverId() { return receiverId; }
    public void setReceiverId(UUID receiverId) { this.receiverId = receiverId; }

    public UUID getGroupId() { return groupId; }
    public void setGroupId(UUID groupId) { this.groupId = groupId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
