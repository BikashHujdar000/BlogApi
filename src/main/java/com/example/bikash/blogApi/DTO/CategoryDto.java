package com.example.bikash.blogApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 3,message = " Title should be at least 3 word")
    private String categoryTitle;

    @NotEmpty
    private String categoryDescription;

    
}
