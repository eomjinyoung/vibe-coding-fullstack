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

    public List<Post> findPosts(int page, int size) {
        List<Post> allPosts = postRepository.findAll();
        int totalCount = allPosts.size();
        int fromIndex = (page - 1) * size;
        if (fromIndex >= totalCount) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + size, totalCount);
        return allPosts.subList(fromIndex, toIndex);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) postRepository.count() / size);
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

    public void deletePost(Long no) {
        postRepository.deleteByNo(no);
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
