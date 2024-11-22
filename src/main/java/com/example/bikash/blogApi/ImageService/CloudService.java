package com.example.bikash.blogApi.ImageService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudService {

    @Autowired
    private Cloudinary cloudinary;



    // Upload image to Cloudinary and return the URL
    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult =this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("url");  // Assuming the result contains a URL field
    }


}
