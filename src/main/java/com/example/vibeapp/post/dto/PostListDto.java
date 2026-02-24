package com.example.vibeapp.post.dto;

import com.example.vibeapp.post.Post;
import java.time.LocalDateTime;

public class PostListDto {
    private Long no;
    private String title;
    private LocalDateTime createdAt;
    private Integer views;

    public static PostListDto from(Post post) {
        if (post == null)
            return null;
        PostListDto dto = new PostListDto();
        dto.no = post.getNo();
        dto.title = post.getTitle();
        dto.createdAt = post.getCreatedAt();
        dto.views = post.getViews();
        return dto;
    }

    public Long getNo() {
        return no;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getViews() {
        return views;
    }
}
