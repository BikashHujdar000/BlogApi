package com.example.bikash.blogApi.Entities;


import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
  private  String name;

  @Column(unique = true)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String password; 
  private String about;

      @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post>posts = new ArrayList<>();

      @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  List<Comment> comments = new ArrayList<>();

}
