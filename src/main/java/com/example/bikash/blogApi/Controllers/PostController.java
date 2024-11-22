package com.example.bikash.blogApi.Controllers;


import org.apache.catalina.connector.Response;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.example.bikash.blogApi.DTO.PostDto;
import com.example.bikash.blogApi.DTO.PostResponse;
import com.example.bikash.blogApi.Entities.Post;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.Services.PostService;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // create 

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestPart("postDto") PostDto postDto,
            @RequestPart("files") MultipartFile[] files,
            @PathVariable("userId") Integer userId,
            @PathVariable("categoryId") Integer categoryId)
    {  
     
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId,files);

        return new ResponseEntity<>(createdPost,HttpStatus.CREATED);

    }


    //update post 

    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost( 
        @RequestBody PostDto postDto,
        @PathVariable("postId")Integer postId
        ) 
    {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        
        return  new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }




    //getBy users 

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId) {
       
        List<PostDto> posts  =   this.postService.getpostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    
    // get by category 

    @GetMapping("/get/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategor (@PathVariable("categoryId") Integer categoryId) {
        List<PostDto> posts  =   this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

   // get all posts 
   @GetMapping("/get/posts")
   public ResponseEntity<PostResponse> getAllPosts(
    @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
    @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
    @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy

   ) {

    PostResponse posts = this.postService.getAllPosts(pageNumber,pageSize,sortBy);

    return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
       
   }

   // get Post By Id 
   @GetMapping("/posts/{postId}")
   public ResponseEntity<PostDto> getPostById( @PathVariable("postId") Integer postId) 
    {

      PostDto postDto=   this.postService.getPostById(postId);
      return  new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
   }
   
   //  delete post 
   @DeleteMapping("/posts/{postId}")
   public ResponseEntity<ApiResponse> deletePost( @PathVariable("postId") Integer postId)
   {
         this.postService.deletePost(postId);
         return new ResponseEntity<ApiResponse> ( new ApiResponse("Deleated Success",true),HttpStatus.OK);
   }


    //search post by keyword 

    @GetMapping("/posts/search/{key}")
    public  ResponseEntity<List<PostDto>> getPostsbyTitle(@PathVariable("key") String key) {
        
       List<PostDto> postDtos =   this.postService.searchPosts(key);
       return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }
    

}
