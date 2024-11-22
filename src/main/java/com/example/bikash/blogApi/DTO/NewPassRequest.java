package com.example.bikash.blogApi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPassRequest {

    private  String email;
    private  String otp;
    private  String newPassword;
}
