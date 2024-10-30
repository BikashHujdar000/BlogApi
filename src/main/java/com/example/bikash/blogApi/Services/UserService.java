package com.example.bikash.blogApi.Services;

import com.example.bikash.blogApi.DTO.UserDTo;

import java.util.List;

public interface UserService {

    UserDTo createUser(UserDTo userDTo);
    UserDTo updateUser(UserDTo userDTo,Integer id);
    UserDTo getUserById(Integer id);
    List<UserDTo> getAllUsers();
    void deleteUser(Integer id);


}
