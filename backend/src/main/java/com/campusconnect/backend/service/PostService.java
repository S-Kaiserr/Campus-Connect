package com.campusconnect.backend.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.campusconnect.backend.dto.CreatePostRequest;
import com.campusconnect.backend.model.Post;
import com.campusconnect.backend.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPostsForGroup(UUID groupId) {
        return postRepository.findByGroupIdOrderByCreatedAtDesc(groupId);
    }

    public List<Post> getPostsByAuthor(UUID authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    public Post createPost(UUID groupId, CreatePostRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new RuntimeException("Post content cannot be empty");
        }

        Post post = new Post();
        post.setGroupId(groupId);
        post.setAuthorId(request.getAuthorId());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedAt(Instant.now());

        return postRepository.save(post);
    }

    public void deletePost(UUID postId) {
        postRepository.deleteById(postId);
    }
}
