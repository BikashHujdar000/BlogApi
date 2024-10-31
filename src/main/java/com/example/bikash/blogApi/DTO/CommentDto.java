package com.example.bikash.blogApi.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private  int id;
    private String content;
}

