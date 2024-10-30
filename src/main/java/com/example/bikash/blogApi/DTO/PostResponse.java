package com.example.bikash.blogApi.DTO;
import java.util.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {
    
   private List<PostDto> content;
   private int pageNumber;
   private int pageSize;
   private long totalElements;
   private int totalPages;
   private boolean lastPage;

    
}
