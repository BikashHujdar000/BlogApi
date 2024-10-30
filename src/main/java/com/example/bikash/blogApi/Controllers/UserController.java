package com.example.bikash.blogApi.Controllers;

import com.example.bikash.blogApi.DTO.UserDTo;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //post - creaate user
    @PostMapping("/")
  public ResponseEntity<UserDTo> createUser( @Valid @RequestBody UserDTo userDTo)
  {
      UserDTo createdUserDto = userService.createUser(userDTo);
      return  new  ResponseEntity<>(createdUserDto,HttpStatus.CREATED );

  }


    // get user all
   @GetMapping("/")
     public  ResponseEntity<List<UserDTo>> getAllUsers()
     {
          List<UserDTo>allUsers =  this.userService.getAllUsers();
          return  ResponseEntity.ok(allUsers);
     }


    // get user by Id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTo> getUserById(@PathVariable("userId") Integer userId) {
        UserDTo userInfo = this.userService.getUserById(userId);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }





    //post update user

  @PutMapping("/{userId}")
    public ResponseEntity<UserDTo> updateUser(  @Valid @RequestBody UserDTo userDTo, @PathVariable("userId") Integer id){

        UserDTo updatedUser =   this.userService.updateUser(userDTo,id);

        return  new ResponseEntity<>(updatedUser, HttpStatus.OK);



  }



    //delete api
    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer id)
    {
         this.userService.deleteUser(id);
         return  new ResponseEntity<>(new ApiResponse("Successfully deleated",true),HttpStatus.OK);
    }





}
