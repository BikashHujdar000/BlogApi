package com.example.bikash.blogApi.Security.JWT;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {

    private  String jwtToken;
}
