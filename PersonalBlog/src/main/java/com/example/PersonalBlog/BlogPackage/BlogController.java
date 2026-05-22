package com.example.PersonalBlog.BlogPackage;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class BlogController {
BlogService blogService;

    public BlogController(BlogService blogService) {
       this.blogService = blogService;
    }
    @GetMapping("/allArticles")
    public List<BlogDTO> articles(){
        return blogService.getArticles();
    }
    @GetMapping("/AritcleById/{articleId}")
    public BlogDTO ArticleById(@Valid @PathVariable int articleId){
        return blogService.FindById(articleId);
    }
    @PostMapping("/create/{username}")
    public String CreateArticle(@Valid @PathVariable String username,@Valid @RequestBody Blog blog){
        blogService.AddNewArticle(username,blog);
        return "Blog created succesfully";
    }
    @DeleteMapping("/Delete/{username}/{articleId}")
    public String DeleteArticle(@Valid @PathVariable int articleId,@Valid @PathVariable String username){
        blogService.DeleteById(articleId,username);
        return "Article deleted ";
    }
    @PutMapping("/Update/{username}/{articleId}")
    public String UpdateArticle(@Valid @PathVariable int articleId,@Valid @PathVariable String username,  @RequestBody Blog blog){
        blogService.update(articleId,username,blog);
        return "Article updated ";
    }

}
