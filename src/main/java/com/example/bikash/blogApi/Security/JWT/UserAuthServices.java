package com.example.bikash.blogApi.Security.JWT;

import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthServices  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        // hamko chahiye vai orginal data from the data baea
        User user =  this.userRepo.findByEmail(email).orElseThrow( ()-> new UsernameNotFoundException("User not Found"));
        // for testing purpose
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        //  to me removed after implementation
        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),Collections.emptyList());
    }
}
