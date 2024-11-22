package com.example.bikash.blogApi.Services;

import com.example.bikash.blogApi.DTO.userDto;
import com.example.bikash.blogApi.DTO.UserRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    userDto createUser(UserRequest userRequest);
    userDto updateUser(userDto userDTo, Integer id);
    userDto getUserById(Integer id);
    List<userDto> getAllUsers();
    void deleteUser(Integer id);

    userDto uploadProfileImage (Integer userId,MultipartFile file);

}
