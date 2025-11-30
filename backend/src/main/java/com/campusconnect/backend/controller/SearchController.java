package com.campusconnect.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.model.Group;
import com.campusconnect.backend.model.User;
import com.campusconnect.backend.repository.GroupRepository;
import com.campusconnect.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/search")
@CrossOrigin
public class SearchController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public SearchController(GroupRepository groupRepository,
                            UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> searchGroups(@RequestParam String query) {
        List<Group> byName = groupRepository.findByNameContainingIgnoreCase(query);
        List<Group> bySubject = groupRepository.findBySubjectContainingIgnoreCase(query);

        // quick merge without the duplicates
        bySubject.stream()
                .filter(g -> !byName.contains(g))
                .forEach(byName::add);

        return ResponseEntity.ok(byName);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        return ResponseEntity.ok(userRepository.findByNameContainingIgnoreCase(query));
    }
}
