package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostTagRepository {
    void save(PostTag postTag);

    void deleteByPostNo(@Param("postNo") Long postNo);

    List<PostTag> findByPostNo(@Param("postNo") Long postNo);
}
