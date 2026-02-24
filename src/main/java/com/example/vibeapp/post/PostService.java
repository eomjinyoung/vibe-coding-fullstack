package com.example.vibeapp.post;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll(int page, int size) {
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

    public Post view(Long no) {
        postRepository.increaseViews(no);
        return postRepository.findById(no);
    }

    public Post findById(Long no) {
        return postRepository.findById(no);
    }

    public void update(Long no, String title, String content) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(java.time.LocalDateTime.now());
        }
    }

    public void delete(Long no) {
        postRepository.deleteById(no);
    }

    public void create(String title, String content) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(java.time.LocalDateTime.now());
        post.setUpdatedAt(null);
        post.setViews(0);
        postRepository.save(post);
    }
}
