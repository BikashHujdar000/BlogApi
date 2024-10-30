package com.example.bikash.blogApi.Services;
import java.util.*;
import com.example.bikash.blogApi.DTO.PostDto;
import com.example.bikash.blogApi.DTO.PostResponse;
import com.example.bikash.blogApi.Entities.Post;

public interface PostService {

//create
PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

//update

PostDto updatePost(PostDto postDto,Integer postId);

//delete post 
 void deletePost(Integer postId);


 // get all posts  // now  using pagination and sorting over here from pageable  and make sure to change the post response for beetter user 

 PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBY);

 // getPostByID

 PostDto getPostById(Integer postId);

    // getall post by category 

     List<PostDto>getPostByCategory(Integer categoeyId);
     

     // get all post by  user

     List<PostDto> getpostsByUser(Integer userId);

     // get alll post by search keywords 
     List<PostDto> searchPosts(String keyword);

}  