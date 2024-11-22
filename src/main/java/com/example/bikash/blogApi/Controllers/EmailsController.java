package com.example.bikash.blogApi.Controllers;

import com.example.bikash.blogApi.DTO.NewPassRequest;
import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.Repositories.UserRepo;
import com.example.bikash.blogApi.Services.EmailService;
import com.example.bikash.blogApi.Services.Implementation.UserServiceImplemenation;
import com.example.bikash.blogApi.Services.OtpService;
import jakarta.mail.MessagingException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailsController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email) {

        // first lets genrate otp


        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP


        // saving the otp service in redis file

        this.otpService.saveOtp(email, otp);


        //sout // let's check also by getting the otp

        String stordRedix = this.otpService.getOtp(email);


        String body = " Your OTP for password reset is : " + otp + ".  This will expire in 5 minute";


        try {
            emailService.sendEmail(email, "Reset Password using Otp", body);

        } catch (MessagingException e) {
            return ResponseEntity.status(500).body(" Email sending failed.  please try again later");
        }

        return new ResponseEntity<>("Email send successfully", HttpStatus.OK);

    }


    @PostMapping("/newPassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody NewPassRequest request) {

        //setting the user Details

        User user = this.userRepo. findByEmail(request.getEmail()).get();
        String hashedPassword = passwordEncoder.encode((request.getNewPassword()));
        user.setPassword(hashedPassword);

        if(otpService.validateOtp(request.getEmail(), request.getOtp()))
        {
            userRepo.save(user);
            ApiResponse response = new ApiResponse("Password Changed Successfully",true);
            return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }
        else {
            ApiResponse response = new ApiResponse("Token Expired ,please try again later ",true);
            return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }



    }


}




