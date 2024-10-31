package com.example.bikash.blogApi.Entities;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import jakarta.persistence.*;

import java.util.*;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
 

   



}
