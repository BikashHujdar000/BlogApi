package com.example.bikash.blogApi.Services.Implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


import com.cloudinary.Cloudinary;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.ImageService.CloudService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.bikash.blogApi.DTO.PostDto;
import com.example.bikash.blogApi.DTO.PostResponse;
import com.example.bikash.blogApi.Entities.Category;
import com.example.bikash.blogApi.Entities.Post;
import com.example.bikash.blogApi.Entities.User;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Repositories.CategoryRepo;
import com.example.bikash.blogApi.Repositories.PostRepo;
import com.example.bikash.blogApi.Repositories.UserRepo;
import com.example.bikash.blogApi.Services.PostService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CloudService cloudService;

    @Autowired
   private  PostRepo postRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private UserRepo userRepo;

   @Autowired
   private CategoryRepo categoryRepo;


   @Override
   public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId, MultipartFile[] files) {


       User user = this.userRepo.findById(userId)
               .orElseThrow(() -> new ResourceNotFound("User", "userId", userId));

       Category category = this.categoryRepo.findById(categoryId)
               .orElseThrow(() -> new ResourceNotFound("Category", "categoryId", categoryId));


       Post post = this.modelMapper.map(postDto, Post.class);
       post.setAddedDate(new Date());
       post.setUser(user);
       post.setCategory(category);


       List<String> imagesList = new ArrayList<>();


       try {
           for (MultipartFile file : files) {
               String imageLink = this.cloudService.uploadImage(file); // Upload image and get the URL
               imagesList.add(imageLink);
           }

      // if its empty i am not going to set
           if (!imagesList.isEmpty()) {
               post.setPostImages(imagesList);
           }

       } catch (IOException e) {
           // Handle error gracefully and provide a clear message
           throw new IllegalStateException("Failed to upload images due to server error", e);
       }

       Post savedPost = this.postRepo.save(post);


       return this.modelMapper.map(savedPost, PostDto.class);

   }

   

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","PostId",postId));
        post.setAddedDate(postDto.getAddedDate());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
         Post savedPost = this.postRepo.save(post);

         return this.modelMapper.map(savedPost, PostDto.class);

    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("post","postId",postId));
        this.postRepo.delete(post);
        
    }

    @Override
    public PostResponse getAllPosts( Integer pageNumber , Integer pageSize,String sortBy) {
     
        Pageable p = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
        Page<Post> postPage = this.postRepo.findAll(p);

        List<Post>posts =  postPage.getContent();
        List<PostDto> postDtos = posts.stream().map((tpost)->this.modelMapper.map(tpost, PostDto.class)).collect(Collectors.toList());
       
       
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setLastPage(postPage.isLast());
        postResponse.setTotalPages(postPage.getTotalPages());
       
        return postResponse;

    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","postId",postId));

       return  this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category","categoryId",categoryId));
       
            List<Post> posts = this.postRepo.findByCategory(category);
            List<PostDto> rposts=  posts.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());

            return rposts;
    }

    @Override
    public List<PostDto> getpostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "userId",userId));
        
        List<Post>posts = this.postRepo.findByUser(user);

        List<PostDto> rposts=  posts.stream().map((p)->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());

        return rposts;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

         List <Post> posts= this.postRepo.searchByTitle("%"+keyword+"%");

         List<PostDto> postDtos =  posts.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
       
    return postDtos;
    }

   
    
}
