package com.example.PersonalBlog.BlogPackage;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
public class BlogDTO {
  private    Date date;
  private    String title;
  private    String content;
  private Blog_userDto blog_userDto;

  public Blog_userDto getBlog_userDto() {
    return blog_userDto;
  }

  public void setBlog_userDto(Blog_userDto blog_userDto) {
    this.blog_userDto = blog_userDto;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
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


}
