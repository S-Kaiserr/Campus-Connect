package com.campusconnect.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.model.Group;

public interface GroupRepository extends JpaRepository<Group, UUID> {

    List<Group> findBySubjectContainingIgnoreCase(String subject);

    List<Group> findByNameContainingIgnoreCase(String name);
}
