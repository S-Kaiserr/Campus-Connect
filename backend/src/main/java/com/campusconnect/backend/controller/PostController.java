package com.campusconnect.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.backend.dto.CreatePostRequest;
import com.campusconnect.backend.model.Post;
import com.campusconnect.backend.service.PostService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Get all posts for a group
    @GetMapping("/groups/{groupId}/posts")
    public ResponseEntity<List<Post>> getPostsForGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(postService.getPostsForGroup(groupId));
    }

    // Create a new post in a group
    @PostMapping("/groups/{groupId}/posts")
    public ResponseEntity<Post> createPost(
            @PathVariable UUID groupId,
            @RequestBody CreatePostRequest request) {

        Post saved = postService.createPost(groupId, request);
        return ResponseEntity.ok(saved);
    }

    // Optional: list posts by author (for profile page, etc.)
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByAuthor(@PathVariable UUID userId) {
        return ResponseEntity.ok(postService.getPostsByAuthor(userId));
    }

    // Optional: delete a post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}