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
    @PostMapping("/create")
    public String CreateArticle(@Valid @RequestBody Blog blog){
        blogService.AddNewArticle(blog);
        return "Blog created succesfully";
    }
    @DeleteMapping("/Delete/{articleId}")
    public String DeleteArticle(@Valid @PathVariable int articleId){
        blogService.DeleteById(articleId);
        return "Article deleted ";
    }
    @PutMapping("/Update/{articleId}")
    public String UpdateArticle(@Valid @PathVariable int articleId, @Valid @RequestBody Blog blog){
        blogService.update(articleId,blog);
        return "Article updated ";
    }

}
