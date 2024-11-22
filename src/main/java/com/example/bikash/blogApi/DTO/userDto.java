package com.example.bikash.blogApi.DTO;


import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDto {

    private int id;

    private String name;

    private String email;

    private String about;

    private List<String> profileImages;

    private List<CommentDto> comments = new ArrayList<>();

}
