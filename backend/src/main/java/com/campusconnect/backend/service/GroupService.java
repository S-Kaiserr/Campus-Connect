package com.campusconnect.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.backend.model.Group;
import com.campusconnect.backend.repository.GroupRepository;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }
}