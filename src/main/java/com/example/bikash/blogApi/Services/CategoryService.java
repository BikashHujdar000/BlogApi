package com.example.bikash.blogApi.Services;
import java.util.*;

import com.example.bikash.blogApi.DTO.CategoryDto;

public interface CategoryService {

    // create 
  CategoryDto createCategory(CategoryDto categoryDto);

    // update 
  CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

// delete 
void deleteCategory(Integer categoryId);
// getsingle 
 CategoryDto  getCategory(Integer categoryId);

    //getall 

List<CategoryDto> getCategories();

    
}
