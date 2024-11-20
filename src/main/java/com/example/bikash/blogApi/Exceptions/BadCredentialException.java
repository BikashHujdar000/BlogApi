package com.example.bikash.blogApi.Exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadCredentialException extends  RuntimeException {
    private  String message;

    public BadCredentialException(String message) {
        super(String.format("%s",message));
        this.message = message;

    }
}
