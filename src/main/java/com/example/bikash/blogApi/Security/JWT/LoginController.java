package com.example.bikash.blogApi.Security.JWT;

import com.example.bikash.blogApi.DTO.userDto;
import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Exceptions.BadCredentialException;
import com.example.bikash.blogApi.Repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private  UserAuthServices userAuth;

    @Autowired
    private  JwtUtils jwtUtils;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;






    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {


        try {
            this.authManager.authenticate(
                    new UsernamePasswordAuthenticationToken( loginRequest.getEmail(),loginRequest.getPassword()));
        }
        catch (AuthenticationException e)
        {
           // return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            throw  new BadCredentialException("Bad Credential");
        }

        // authenticate heoney k baad mujher ussre Details banan padega


        UserDetails userDetails;
        try {
            userDetails = this.userAuth.loadUserByUsername(loginRequest.getEmail());
        }
        catch (UsernameNotFoundException e)
        {
//             return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            throw  new BadCredentialException("Bad Credential");
        }



       User user = userRepo.findByEmail(userDetails.getUsername()).get();


        userDto userDTo = this.modelMapper.map(user, userDto.class);

      String token = this.jwtUtils.generateToken(userDetails.getUsername());




        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setJwtToken(token);
        loginResponse.setUserDto(userDTo);
        return  new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.ACCEPTED);

    }

}