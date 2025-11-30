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
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.JoinGroupRequest;
import com.campusconnect.backend.model.Group;
import com.campusconnect.backend.service.UserGroupService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MembershipController {

    private final UserGroupService userGroupService;

    public MembershipController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping("/groups/{groupId}/join")
    public ResponseEntity<Void> joinGroup(
            @PathVariable UUID groupId,
            @RequestBody JoinGroupRequest request) {

        userGroupService.joinGroup(request.getUserId(), groupId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/groups/{groupId}/leave")
    public ResponseEntity<Void> leaveGroup(
            @PathVariable UUID groupId,
            @RequestBody JoinGroupRequest request) {

        userGroupService.leaveGroup(request.getUserId(), groupId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{userId}/groups")
    public ResponseEntity<List<Group>> getUserGroups(@PathVariable UUID userId) {
        return ResponseEntity.ok(userGroupService.getGroupsForUser(userId));
    }
}
