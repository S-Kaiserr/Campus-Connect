package com.campusconnect.backend.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.backend.model.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByGroupIdOrderByCreatedAtDesc(UUID groupId);

    List<Post> findByAuthorId(UUID authorId);
}
