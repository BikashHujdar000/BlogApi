package com.example.bikash.blogApi.Security.JWT;

import com.example.bikash.blogApi.DTO.UserDTo;
import com.example.bikash.blogApi.Entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private  String jwtToken;
    private UserDTo userDTo ;
}
