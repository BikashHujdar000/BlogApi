package com.example.bikash.blogApi.Repositories;

import com.example.bikash.blogApi.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comment,Integer> {
}
