package com.springbootblog.springbootblogrestapi.service;

import com.springbootblog.springbootblogrestapi.dto.PostDto;
import com.springbootblog.springbootblogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

    List<PostDto> getPostsByCategory(Long categoryId);
}
