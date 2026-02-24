package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
    }

    public List<PostListDto> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return postRepository.findAll(offset, size).stream()
                .map(PostListDto::from)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) postRepository.count() / size);
    }

    public PostResponseDto view(Long no) {
        postRepository.increaseViews(no);
        Post post = postRepository.findById(no);
        String tags = getTagsAsString(no);
        return PostResponseDto.from(post, tags);
    }

    public PostResponseDto findById(Long no) {
        Post post = postRepository.findById(no);
        String tags = getTagsAsString(no);
        return PostResponseDto.from(post, tags);
    }

    private String getTagsAsString(Long no) {
        return postTagRepository.findByPostNo(no).stream()
                .map(PostTag::getTagName)
                .collect(Collectors.joining(", "));
    }

    @Transactional
    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findById(no);
        if (post != null) {
            post.setTitle(dto.title());
            post.setContent(dto.content());
            post.setUpdatedAt(java.time.LocalDateTime.now());
            postRepository.update(post);

            postTagRepository.deleteByPostNo(no);
            saveTags(no, dto.tags());
        }
    }

    @Transactional
    public void delete(Long no) {
        postRepository.deleteById(no);
    }

    @Transactional
    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
        saveTags(post.getNo(), dto.tags());
    }

    private void saveTags(Long postNo, String tagsStr) {
        if (tagsStr == null || tagsStr.isBlank()) {
            return;
        }

        String[] tags = tagsStr.split(",");
        for (String tagName : tags) {
            String trimmed = tagName.trim();
            if (!trimmed.isEmpty()) {
                postTagRepository.save(new PostTag(null, postNo, trimmed));
            }
        }
    }
}
