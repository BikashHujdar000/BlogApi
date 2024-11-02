//package com.example.bikash.blogApi.Entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//
//import jakarta.persistence.*;
//
//import java.util.*;
//import java.util.Date;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "posts")
//@Data
//@NoArgsConstructor
//public class Post {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer postId;
//    private String title;
//    private String content;
//    private String postImage;
//    private Date addedDate;
//
//
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "category_id")
//    private Category category;
//
//    @ManyToOne
//    @JsonIgnore
//    private User user;
//
//
//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Comment> comments = new ArrayList<>();
//
//
//
//
//
//
//}
//
//
package com.example.bikash.blogApi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String title;
    private String content;
    private String postImage;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    // Prevent infinite recursion when serializing Post -> Comment -> Post
    private List<Comment> comments = new ArrayList<>();
}
