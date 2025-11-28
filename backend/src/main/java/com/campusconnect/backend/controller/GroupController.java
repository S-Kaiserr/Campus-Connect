package com.campusconnect.backend.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.CreateGroupRequest;
import com.campusconnect.backend.model.Group;
import com.campusconnect.backend.service.GroupService;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setSubject(request.getSubject());
        group.setDescription(request.getDescription());

        group.setCreatedBy(request.getCreatedBy());

        group.setCreatedAt(java.time.Instant.now());

        Group saved = groupService.createGroup(group);
        return ResponseEntity.ok(saved);

    }
}