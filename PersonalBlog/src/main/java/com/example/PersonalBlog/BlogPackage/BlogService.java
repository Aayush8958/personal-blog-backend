package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.NotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
BlogRepo blogRepo;
BlogDTO blogDTO;
ToDto toDto;

    public BlogService(BlogRepo blogRepo,BlogDTO blogDTO,ToDto toDto) {
        this.blogRepo = blogRepo;
        this.blogDTO=blogDTO;
        this.toDto=toDto;
    }



    public List<BlogDTO> getArticles() {
            if(blogRepo.findAll().isEmpty()) throw new NotFound("No data is found ");
        return toDto.toDTOList(blogRepo.findAll());
    }



    public BlogDTO FindById(int articleId){
        if(blogRepo.findById(articleId).isEmpty()) throw new NotFound("No data found related to this Article-Id");
        return toDto.toDTO(blogRepo.getById(articleId));
    }

    public void AddNewArticle(Blog blog) {

        blogRepo.save(blog);
    }

    public void DeleteById(int articleId) {
        if(blogRepo.findById(articleId).isEmpty()) throw new NotFound("No data found related to this Article-Id");

        blogRepo.deleteById(articleId);
    }
    @Transactional
    public void update(int articleId,Blog blog) {
        if(blogRepo.findById(articleId).isEmpty()) throw new NotFound("No data found related to this Article-Id");

        Blog blog1=blogRepo.getById(articleId);
        blog1.setContent(blog.getContent());
    }
}
