package com.example.bikash.blogApi.Security.JWT;

import com.example.bikash.blogApi.DTO.userDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private  String jwtToken;
    private userDto userDto ;
}
