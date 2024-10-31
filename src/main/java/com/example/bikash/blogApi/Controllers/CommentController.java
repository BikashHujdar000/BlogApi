package com.example.bikash.blogApi.Controllers;

import com.example.bikash.blogApi.DTO.CommentDto;
import com.example.bikash.blogApi.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable("postId") Integer postId,
                                                    @PathVariable("userId") Integer userId)

    {

       CommentDto commentDto1 =   this.commentService.createComment(commentDto,postId,userId);

       return   new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }


}
