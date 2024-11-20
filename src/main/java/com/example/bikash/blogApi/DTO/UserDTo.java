package com.example.bikash.blogApi.DTO;


import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTo {

    private  int id;

    private String name;

    private  String email;

    private  String about;


    private List<CommentDto> comments = new ArrayList<>();

}
