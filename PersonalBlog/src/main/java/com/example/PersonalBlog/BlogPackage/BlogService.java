package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.Forbidden;
import com.example.PersonalBlog.Exceptions.NotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    Blog_user blogUser;
    UserRepo userRepo;
BlogRepo blogRepo;
BlogDTO blogDTO;
ToDto toDto;

    public BlogService(BlogRepo blogRepo,BlogDTO blogDTO,ToDto toDto,UserRepo userRepo,Blog_user blogUser) {
        this.blogRepo = blogRepo;
        this.blogDTO=blogDTO;
        this.toDto=toDto;
        this.userRepo=userRepo;
        this.blogUser=blogUser;
    }

    
    public List<BlogDTO> getArticles() {
            if(blogRepo.findAll().isEmpty()) throw new NotFound("No data is found ");

         return   toDto.toDTOList(blogRepo.findAll());
    }



    public BlogDTO FindById(int articleId){
        if(blogRepo.findById(articleId).isEmpty()) throw new NotFound("No data found related to this Article-Id");
        return toDto.toDTO(blogRepo.getById(articleId));
    }

    public void AddNewArticle(String useranme,Blog blog) {
     if(!userRepo.existsByUsername(useranme))
         throw new NotFound("No UserName found");
     Blog_user author=userRepo.findByUsername(useranme);
        blog.setAuthor(author);
        blogRepo.save(blog);
    }
@Transactional
    public void DeleteById(int articleId,String username) {
        Blog blog1 = blogRepo.findById(articleId)
                .orElseThrow(() -> new NotFound("No data found related to this Article-Id"));

        if(blog1.getAuthor()==null || !blog1.getAuthor().getUsername().equals(username))
            throw new Forbidden("No userName  exists ");

        blogRepo.deleteById(articleId);
    }
    @Transactional
    public void update(int articleId,String username,Blog blog) {
        Blog blog1 = blogRepo.findById(articleId)
                .orElseThrow(() -> new NotFound("No data found related to this Article-Id"));

        if(blog1.getAuthor()==null || !blog1.getAuthor().getUsername().equals(username))
            throw new Forbidden("No userName  exists ");

        blog1.setContent(blog.getContent());
    }
}
