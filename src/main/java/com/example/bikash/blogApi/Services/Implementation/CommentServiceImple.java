package com.example.bikash.blogApi.Services.Implementation;

import com.example.bikash.blogApi.DTO.CommentDto;
import com.example.bikash.blogApi.Entities.Comment;
import com.example.bikash.blogApi.Entities.Post;
import com.example.bikash.blogApi.Exceptions.ResourceNotFound;
import com.example.bikash.blogApi.Repositories.CommentRepo;
import com.example.bikash.blogApi.Repositories.PostRepo;
import com.example.bikash.blogApi.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImple  implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepo commentRepo;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this
                .postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","postId",postId));

      Comment comment = this.modelMapper.map(commentDto,Comment.class);
      comment.setPost(post);

       Comment savadComment =   this.commentRepo.save(comment);

       return  this.modelMapper.map(savadComment,CommentDto.class);
    }
}
