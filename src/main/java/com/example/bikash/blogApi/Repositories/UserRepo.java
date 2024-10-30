package com.example.bikash.blogApi.Repositories;

import com.example.bikash.blogApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepo extends JpaRepository<User,Integer> {
}
