package com.example.vibeapp.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostTagRepository {
    void save(PostTag postTag);

    void deleteByPostNo(@Param("postNo") Long postNo);
}
