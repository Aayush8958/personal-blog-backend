package com.example.PersonalBlog.BlogPackage;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Can't leave empty")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id",nullable = false)
    private Blog_user author;

    public Blog_user getAuthor() {
        return author;
    }

    public void setAuthor(Blog_user author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog(){}

}
