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


    public String uploadImage(MultipartFile file) throws IOException {

     Map uploadResult  = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        return uploadResult.get("url").toString();

    }


}
