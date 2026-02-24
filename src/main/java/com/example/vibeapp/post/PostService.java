package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostListDto> findAll(int page, int size) {
        List<Post> allPosts = postRepository.findAll();
        int totalCount = allPosts.size();
        int fromIndex = (page - 1) * size;
        if (fromIndex >= totalCount) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + size, totalCount);
        return allPosts.subList(fromIndex, toIndex).stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) postRepository.count() / size);
    }

    public PostResponseDto view(Long no) {
        postRepository.increaseViews(no);
        return PostResponseDto.from(postRepository.findById(no));
    }

    public PostResponseDto findById(Long no) {
        return PostResponseDto.from(postRepository.findById(no));
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(dto.title());
            post.setContent(dto.content());
            post.setUpdatedAt(java.time.LocalDateTime.now());
        }
    }

    public void delete(Long no) {
        postRepository.deleteById(no);
    }

    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
    }
}
