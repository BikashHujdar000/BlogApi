package com.example.bikash.blogApi.DTO;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String postId;

    private  String title;

    private String content;

    private String  postImage;

    private Date addedDate;
     
    private userDto user;


    // usisng as image file
    private MultipartFile file;


    private CategoryDto category;

    // it will return all the commens from list
    private List<CommentDto> comments = new ArrayList<>();





}
