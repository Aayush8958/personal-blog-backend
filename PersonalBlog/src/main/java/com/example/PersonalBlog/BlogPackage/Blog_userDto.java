package com.example.PersonalBlog.BlogPackage;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Blog_userDto {
    private int id;
    private String username;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
