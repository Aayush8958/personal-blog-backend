package com.example.PersonalBlog.BlogPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends JpaRepository <Blog,Integer> {
    List<Blog> findByAuthorUsername(String username);
}
