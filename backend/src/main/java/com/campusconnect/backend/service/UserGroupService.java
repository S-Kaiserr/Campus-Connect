package com.campusconnect.backend.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.campusconnect.backend.model.Group;
import com.campusconnect.backend.model.UserGroup;
import com.campusconnect.backend.repository.GroupRepository;
import com.campusconnect.backend.repository.UserGroupRepository;

@Service
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final GroupRepository groupRepository;

    public UserGroupService(UserGroupRepository userGroupRepository,
                            GroupRepository groupRepository) {
        this.userGroupRepository = userGroupRepository;
        this.groupRepository = groupRepository;
    }

    public void joinGroup(UUID userId, UUID groupId) {
        if (!groupRepository.existsById(groupId)) {
            throw new RuntimeException("Group not found");
        }

        if (userGroupRepository.existsByUserIdAndGroupId(userId, groupId)) {
            // Already a member; silently ignore or throw if you want
            return;
        }

        UserGroup ug = new UserGroup();
        ug.setUserId(userId);
        ug.setGroupId(groupId);
        ug.setJoinedAt(Instant.now());

        userGroupRepository.save(ug);
    }

    public void leaveGroup(UUID userId, UUID groupId) {
        userGroupRepository.findByUserIdAndGroupId(userId, groupId)
                .ifPresent(userGroupRepository::delete);
    }

    public List<Group> getGroupsForUser(UUID userId) {
        List<UserGroup> memberships = userGroupRepository.findByUserId(userId);
        List<UUID> groupIds = memberships.stream()
                .map(UserGroup::getGroupId)
                .collect(Collectors.toList());

        return groupRepository.findAllById(groupIds);
    }
}
