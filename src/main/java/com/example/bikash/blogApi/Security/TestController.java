package com.example.bikash.blogApi.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/home")
    public ResponseEntity<String> home()
    {
        return   new ResponseEntity<>(" This is authenticated api call", HttpStatus.OK);
    }

}
