package com.example.bikash.blogApi.Security.JWT;

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

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private  UserAuthServices userAuth;

    @Autowired
    private  JwtUtils jwtUtils;






    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {


        try {
            this.authManager.authenticate(
                    new UsernamePasswordAuthenticationToken( loginRequest.getEmail(),loginRequest.getPassword()));
        }
        catch (AuthenticationException e)
        {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // authenticate heoney k baad mujher ussre Details banan padega


        UserDetails userDetails;
        try {
            userDetails = this.userAuth.loadUserByUsername(loginRequest.getEmail());
        }
        catch (UsernameNotFoundException e)
        {
             return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }




      String token = this.jwtUtils.generateToken(userDetails.getUsername());


        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setJwtToken(token);
        return  new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.ACCEPTED);

    }

}
