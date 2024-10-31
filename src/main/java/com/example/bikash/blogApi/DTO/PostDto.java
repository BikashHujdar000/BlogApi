package com.example.bikash.blogApi.DTO;
import java.util.*;



import com.example.bikash.blogApi.Entities.Category;

import com.example.bikash.blogApi.Entities.Comment;
import com.example.bikash.blogApi.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String postId;

    private  String title;

    private String content;

    private String imageName;

    private Date addedDate;
     
    private UserDTo user;

    private CategoryDto category;

    // it will return all the commens from list
    private List<CommentDto> comments = new ArrayList<>();





}
