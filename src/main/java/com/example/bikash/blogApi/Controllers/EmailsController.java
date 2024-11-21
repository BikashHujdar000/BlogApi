package com.example.bikash.blogApi.Controllers;

import com.example.bikash.blogApi.Services.EmailService;
import com.example.bikash.blogApi.Services.OtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailsController {



    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;



    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgetPassword(@RequestParam String email) {

        // first lets genrate otp



        String otp = String.valueOf((int)(Math.random() * 900000) + 100000); // 6-digit OTP


        // saving the otp service in redis file

        this.otpService.saveOtp(email,otp);



        //sout // let's check also by getting the otp

      String stordRedix=   this.otpService.getOtp(email);

        System.out.println(" Stored otp " +stordRedix);


        String body = " Your OTP for password reset is : " +otp + ".  This will expire in 5 minute";



        System.out.println(body);
       try
       {
           emailService.sendEmail(email,"Reset Password using Otp",body);

       }catch (MessagingException e)
       {
           return  ResponseEntity.status(500).body(" Email sending failed.  please try again later");
       }

     return   new ResponseEntity<>("Email send successfully", HttpStatus.OK);

    }
}





