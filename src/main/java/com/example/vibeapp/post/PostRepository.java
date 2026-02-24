package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PostRepository {
    List<Post> findAll(@Param("offset") int offset, @Param("size") int size);

    long count();

    Post findById(Long no);

    void save(Post post);

    void update(Post post);

    void deleteById(Long no);

    void increaseViews(Long no);
}
