package com.example.PersonalBlog.BlogPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Blog_user,Integer> {

    Blog_user findByUsername(String username);
    boolean existsByUsername(String username);
}
