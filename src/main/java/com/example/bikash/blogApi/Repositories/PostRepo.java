package com.example.bikash.blogApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

import com.example.bikash.blogApi.Entities.Category;
import com.example.bikash.blogApi.Entities.Post;
import com.example.bikash.blogApi.Entities.User;


import java.util.List;


public interface PostRepo extends JpaRepository<Post,Integer> {

    // get all post by user chee hami yaha costom banauxaum  those that retrive teh replation wala api 


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

   @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);

} 
