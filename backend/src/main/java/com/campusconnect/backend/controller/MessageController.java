package com.campusconnect.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.SendMessageRequest;
import com.campusconnect.backend.model.Message;
import com.campusconnect.backend.service.MessageService;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody SendMessageRequest request) {
        Message saved = messageService.sendMessage(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/direct")
    public ResponseEntity<List<Message>> getDirectMessages(
            @RequestParam UUID user1,
            @RequestParam UUID user2) {
        return ResponseEntity.ok(messageService.getDirectMessages(user1, user2));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Message>> getGroupMessages(@PathVariable UUID groupId) {
        return ResponseEntity.ok(messageService.getGroupMessages(groupId));
    }
}
