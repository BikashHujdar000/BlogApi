//package com.example.bikash.blogApi.Entities;
//
//import org.modelmapper.internal.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;
//import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//import java.util.*;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "categories")
//
//public class Category {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer categoryId;
//
//    @Column(name = "title",nullable = false)
//    private String categoryTitle;
//
//    @Column(name = "description")
//    private String categoryDescription;
//
//
//    // one category can have  many posts thats why i am using one to many relation over here
//
//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Post>posts = new ArrayList<>();
//
//
//
//}
//

package com.example.bikash.blogApi.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")

@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "title", nullable = false)
    private String categoryTitle;

    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude// Prevent infinite recursion when serializing Category -> Post -> Category
    private List<Post> posts = new ArrayList<>();
}
