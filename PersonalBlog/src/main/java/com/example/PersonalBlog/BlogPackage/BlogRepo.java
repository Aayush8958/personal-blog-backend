package com.example.PersonalBlog.BlogPackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository <Blog,Integer> {

}
