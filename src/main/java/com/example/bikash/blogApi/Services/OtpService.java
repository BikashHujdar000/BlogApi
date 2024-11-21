package com.example.bikash.blogApi.Services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    private static final int EXPIRE_MINUTES = 5;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveOtp(String key, String otp) {
        redisTemplate.opsForValue().set(key, otp, EXPIRE_MINUTES, TimeUnit.MINUTES);
    }

    public String getOtp(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteOtp(String key) {
        redisTemplate.delete(key);
    }

    public boolean validateOtp(String key, String otp) {
        String savedOtp = getOtp(key);
        return savedOtp != null && savedOtp.equals(otp);
    }
}

