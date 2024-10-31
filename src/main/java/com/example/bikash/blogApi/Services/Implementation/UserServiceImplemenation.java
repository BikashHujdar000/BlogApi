package com.example.bikash.blogApi.Services.Implementation;

import com.example.bikash.blogApi.DTO.UserDTo;
import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Repositories.UserRepo;
import com.example.bikash.blogApi.Services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplemenation implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    //  create , read , update delete
    @Override
    public UserDTo createUser(UserDTo userDTo) {

        // creste user repo
        User user = this.dtoToUser(userDTo);
        User savedUser = this.userRepo.save(user);
        return this.usertoUserDto(savedUser);

    }

    @Override
    public UserDTo updateUser(UserDTo userDTo, Integer id) {

        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("user", "id", id));
        user.setName(userDTo.getName());
        user.setEmail(userDTo.getEmail());
        user.setPassword(userDTo.getPassword());
        user.setAbout(userDTo.getAbout());

        User updatedUser = this.userRepo.save(user);
        return this.usertoUserDto(updatedUser);

    }


    @Override
    public UserDTo getUserById(Integer id) {


        User userRe = this.userRepo.findById(id).
                orElseThrow(() -> new ResourceNotFound("Users", "ID", id));
        return this.usertoUserDto(userRe);

    }


    @Override
    public List<UserDTo> getAllUsers() {


        List<User> users = this.userRepo.findAll();

        List<UserDTo> userDTos = users.stream().map(user -> this.usertoUserDto(user)).collect(Collectors.toList());
        return userDTos;


    }

    @Override
    public void deleteUser(Integer id) {

        User userD = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Users", "ID", id));
        this.userRepo.deleteById(id);
    }


    // for now lets create a userto dto and vice versa methods we will user later on model mapper
    public User dtoToUser(UserDTo userDTo) {

        // User user = new User();
        // user.setId(userDTo.getId());
        // user.setName(userDTo.getName());
        // user.setEmail(userDTo.getEmail());
        // user.setPassword(userDTo.getPassword());
        // user.setAbout(userDTo.getAbout());

      User user =   this.modelMapper.map(userDTo, User.class);
        return user;
    }

    public UserDTo usertoUserDto(User user) {
//        UserDTo userDTo = new UserDTo();
//        userDTo.setId(user.getId());
//        userDTo.setName(user.getName());
//        userDTo.setEmail(user.getEmail());
//        userDTo.setPassword(user.getPassword());
//        userDTo.setAbout(user.getAbout());

        UserDTo userDTo = this.modelMapper.map(user,UserDTo.class);
        return userDTo;
    }


}
