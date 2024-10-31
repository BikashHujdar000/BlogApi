package com.example.bikash.blogApi.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String content;

    //mapping
    @ManyToOne
    @JoinColumn(name = "post_Id")
    private  Post post;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;



}
