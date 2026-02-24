package com.example.vibeapp.service;

import com.example.vibeapp.domain.Post;
import com.example.vibeapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post viewPost(Long no) {
        postRepository.increaseViews(no);
        return postRepository.findByNo(no);
    }

    public Post getPost(Long no) {
        return postRepository.findByNo(no);
    }

    public void updatePost(Long no, String title, String content) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(java.time.LocalDateTime.now());
        }
    }

    public void registerPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(java.time.LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }
}
