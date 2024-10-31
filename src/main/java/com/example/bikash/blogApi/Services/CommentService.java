package com.example.bikash.blogApi.Services;

import com.example.bikash.blogApi.DTO.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId);
}
