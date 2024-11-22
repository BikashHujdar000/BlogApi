package com.example.bikash.blogApi.Controllers;

import com.example.bikash.blogApi.DTO.userDto;
import com.example.bikash.blogApi.DTO.UserRequest;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.Services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //post - creaate user
    @PostMapping("/create")
    public ResponseEntity<userDto> createUser(@Valid @RequestBody UserRequest userRequest) {
        userDto createdUserDto = userService.createUser(userRequest);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

    }


    // get user all
    @GetMapping("/")
    public ResponseEntity<List<userDto>> getAllUsers() {
        List<userDto> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }


    // get user by Id
    @GetMapping("/{userId}")
    public ResponseEntity<userDto> getUserById(@PathVariable("userId") Integer userId) {
        userDto userInfo = this.userService.getUserById(userId);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }


    //post update user

    @PutMapping("/{userId}")
    public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto userDTo, @PathVariable("userId") Integer id) {

        userDto updatedUser = this.userService.updateUser(userDTo, id);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);


    }


    //delete api
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("Successfully deleated", true), HttpStatus.OK);
    }


    // Upload Profile Image for User
    @PostMapping("/{userId}/profile")
    public ResponseEntity<userDto> uploadProfileImage(
            @PathVariable("userId") Integer userId,
            @RequestParam("file") MultipartFile file) {

        userDto updatedUser = this.userService.uploadProfileImage(userId, file);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);


    }

}
