package com.example.bikash.blogApi.Security.JWT;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {


    private  String email;
    private  String password;

}
