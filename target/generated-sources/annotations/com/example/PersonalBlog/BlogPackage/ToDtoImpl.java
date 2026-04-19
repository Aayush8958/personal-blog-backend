package com.example.PersonalBlog.BlogPackage;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-19T22:37:11+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ToDtoImpl implements ToDto {

    @Override
    public BlogDTO toDTO(Blog blog) {
        if ( blog == null ) {
            return null;
        }

        BlogDTO blogDTO = new BlogDTO();

        blogDTO.setDate( blog.getDate() );
        blogDTO.setTitle( blog.getTitle() );
        blogDTO.setContent( blog.getContent() );

        return blogDTO;
    }

    @Override
    public Blog toEntity(BlogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Blog blog = new Blog();

        blog.setDate( dto.getDate() );
        blog.setTitle( dto.getTitle() );
        blog.setContent( dto.getContent() );

        return blog;
    }

    @Override
    public List<BlogDTO> toDTOList(List<Blog> blog) {
        if ( blog == null ) {
            return null;
        }

        List<BlogDTO> list = new ArrayList<BlogDTO>( blog.size() );
        for ( Blog blog1 : blog ) {
            list.add( toDTO( blog1 ) );
        }

        return list;
    }
}
