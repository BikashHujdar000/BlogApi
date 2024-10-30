package com.example.bikash.blogApi.Exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ResourceNotFound extends  RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;


    public ResourceNotFound(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s :%d",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
