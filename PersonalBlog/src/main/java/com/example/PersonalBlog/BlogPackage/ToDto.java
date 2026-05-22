package com.example.PersonalBlog.BlogPackage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDto {
    BlogDTO toDTO(Blog blog);

    Blog toEntity(BlogDTO dto);

    List<BlogDTO> toDTOList(List<Blog> blog);
}
