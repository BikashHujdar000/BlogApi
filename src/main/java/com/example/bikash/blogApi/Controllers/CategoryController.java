package com.example.bikash.blogApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bikash.blogApi.DTO.CategoryDto;
import com.example.bikash.blogApi.Exceptions.ApiResponse;
import com.example.bikash.blogApi.Services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;




@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //create

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto)
    {
          CategoryDto  savedCategory = this.categoryService.createCategory(categoryDto);
          return new ResponseEntity<CategoryDto>(savedCategory,HttpStatus.CREATED);
    }

    //update

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable ("categoryId") Integer categoryId)
    {
          CategoryDto  updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
          return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
    }
    
   
     //delete 

     @DeleteMapping("/{catId}")
     public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
     {
        this.categoryService.deleteCategory(catId);
         return new ResponseEntity<ApiResponse>(new ApiResponse(" Success deleated",true) ,HttpStatus.OK);
     }
     //get by Id

     @GetMapping("/get/{categoryId}")
     public ResponseEntity<CategoryDto>getCategoryById(@PathVariable ("categoryId") Integer categoryId)
     {
         CategoryDto cat = this.categoryService.getCategory(categoryId);
         return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);

     }
     
    


     //get all 

     @GetMapping("/get")
     public ResponseEntity<List<CategoryDto>> getAllCategories()
     {
           List<CategoryDto> categoryDtos =  this.categoryService.getCategories();
               
            return ResponseEntity.ok(categoryDtos);
     }
     

}
