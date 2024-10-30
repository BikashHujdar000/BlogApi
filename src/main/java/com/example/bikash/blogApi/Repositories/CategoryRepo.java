package com.example.bikash.blogApi.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bikash.blogApi.Entities.Category;

public interface CategoryRepo  extends JpaRepository<Category,Integer>{
    
    
}
