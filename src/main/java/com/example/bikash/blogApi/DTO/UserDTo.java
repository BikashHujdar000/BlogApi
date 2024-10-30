package com.example.bikash.blogApi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty
    @Size(min = 4, message = "Username should be greater than 4 characters")
    private String name;


    @NotEmpty
    @Size(min = 5, message = "Password should be a minimum of 5 characters")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number")
    private String password;

    @Email(message = "Enter a valid email")
    @Pattern(regexp = "^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$", message = "Email format is invalid")
    private  String email;


    private  String about;
}
