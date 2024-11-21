package com.example.bikash.blogApi.Services.Implementation;

import com.example.bikash.blogApi.DTO.userDto;
import com.example.bikash.blogApi.DTO.UserRequest;
import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Exceptions.BadCredentialException;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Repositories.UserRepo;
import com.example.bikash.blogApi.Services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplemenation implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //  create , read , update delete
    @Override
    public userDto createUser(UserRequest userRequest) {

        // creste user repo

        // Check if a user already exists with the provided email
        Optional<User> checkUser = this.userRepo.findByEmail(userRequest.getEmail());

        if (checkUser.isPresent()) {
            // If user already exists, throw a custom exception or handle the error gracefully
            throw new BadCredentialException("User with this email already exists");
        }
        // Map userRequest to User entity
        User user = this.modelMapper.map(userRequest, User.class);

        // Encode the user's password
        String password = user.getPassword();
        String hashedPassword = this.passwordEncoder.encode(password);
        user.setPassword(hashedPassword);

        // Save the new user to the database
        User savedUser = this.userRepo.save(user);

        // Return the saved user as a DTO
        return this.usertoUserDto(savedUser);

    }

    @Override
    public userDto updateUser(userDto userDTo, Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("user", "id", id));
        user.setName(userDTo.getName());
        user.setEmail(userDTo.getEmail());
        user.setAbout(userDTo.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.usertoUserDto(updatedUser);

    }


    @Override
    public userDto getUserById(Integer id) {


        User userRe = this.userRepo.findById(id).
                orElseThrow(() -> new ResourceNotFound("Users", "ID", id));
        return this.usertoUserDto(userRe);

    }


    @Override
    public List<userDto> getAllUsers() {


        List<User> users = this.userRepo.findAll();

        List<userDto> userDTos = users.stream().map(user -> this.usertoUserDto(user)).collect(Collectors.toList());
        return userDTos;


    }

    @Override
    public void deleteUser(Integer id) {

        User userD = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Users", "ID", id));
        this.userRepo.deleteById(id);
    }


    // for now lets create a userto dto and vice versa methods we will user later on model mapper
    public User dtoToUser(userDto userDTo) {

        // User user = new User();
        // user.setId(userDTo.getId());
        // user.setName(userDTo.getName());
        // user.setEmail(userDTo.getEmail());
        // user.setPassword(userDTo.getPassword());
        // user.setAbout(userDTo.getAbout());

      User user =   this.modelMapper.map(userDTo, User.class);
        return user;
    }

    public userDto usertoUserDto(User user) {
//        userDto userDTo = new userDto();
//        userDTo.setId(user.getId());
//        userDTo.setName(user.getName());
//        userDTo.setEmail(user.getEmail());
//        userDTo.setPassword(user.getPassword());
//        userDTo.setAbout(user.getAbout());

        userDto userDTo = this.modelMapper.map(user, userDto.class);
        return userDTo;
    }


}
