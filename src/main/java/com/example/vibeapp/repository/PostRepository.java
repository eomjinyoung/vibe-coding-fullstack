package com.example.vibeapp.repository;

import com.example.vibeapp.domain.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private static final List<Post> store = new ArrayList<>();
    private static long sequence = 0L;

    static {
        for (int i = 1; i <= 10; i++) {
            LocalDateTime now = LocalDateTime.now();
            store.add(new Post(
                    ++sequence,
                    "테스트 게시글 제목 " + i,
                    "테스트 게시글 내용입니다. " + i,
                    now,
                    now,
                    (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        return store.stream()
                .sorted((p1, p2) -> p2.getNo().compareTo(p1.getNo()))
                .toList();
    }

    public Post findByNo(Long no) {
        return store.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }

    public void increaseViews(Long no) {
        store.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .ifPresent(post -> post.setViews(post.getViews() + 1));
    }

    public void save(Post post) {
        post.setNo(++sequence);
        store.add(post);
    }
}
