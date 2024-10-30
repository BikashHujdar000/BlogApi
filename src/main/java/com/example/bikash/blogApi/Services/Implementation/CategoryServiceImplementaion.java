package com.example.bikash.blogApi.Services.Implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bikash.blogApi.DTO.CategoryDto;
import com.example.bikash.blogApi.Entities.Category;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Repositories.CategoryRepo;
import com.example.bikash.blogApi.Services.CategoryService;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementaion  implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       

       Category  cat= this.modelMapper.map(categoryDto,Category.class);
       Category  savedCat = this.categoryRepo.save(cat);
       return  this.modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
      
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("category", "categoryId", categoryId));
         cat.setCategoryTitle(categoryDto.getCategoryTitle());
         cat.setCategoryDescription(categoryDto.getCategoryDescription());
          Category savedCat = this.categoryRepo.save(cat);

          return this.modelMapper.map(savedCat, CategoryDto.class);


    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category","CategotyId",categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category","CategotyId",categoryId));
         return this.modelMapper.map(cat, CategoryDto.class);
      


    }

    @Override
    public List<CategoryDto> getCategories() {
       
        List<Category> categories = this.categoryRepo.findAll();

       //
        // List<CategoryDto> categoryDtos =  categories.stream()
        //.map(cat->this.modelMapper.map(categories, CategoryDto.class))
        //.collect(Collectors.toList());
        // 

        List<CategoryDto> categoryDtos = categories.stream()
        .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
        .collect(Collectors.toList());

         return categoryDtos;

    }
    
}
