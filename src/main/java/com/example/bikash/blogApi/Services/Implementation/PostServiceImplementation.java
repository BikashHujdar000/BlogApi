package com.example.bikash.blogApi.Services.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Set;


import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
   private  PostRepo postRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private UserRepo userRepo;

   @Autowired
   private CategoryRepo categoryRepo;


   @Override
   public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
      
   User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "userId",userId));
          
   Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category","categoryId",categoryId));
       

   Post post = this.modelMapper.map(postDto, Post.class);

   post.setImageName("default.png");
   post.setAddedDate(new Date());
   post.setUser(user);
   post.setCategory(category);

   Post savedPost = this.postRepo.save(post);


  return  this.modelMapper.map(savedPost, PostDto.class);

   }

   

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","PostId",postId));
        post.setAddedDate(postDto.getAddedDate());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

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
