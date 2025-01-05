package com.example.mapper;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.entities.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T17:20:47+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryName( dto.getCategoryName() );
        category.setCategoryType( dto.getCategoryType() );

        return category;
    }

    @Override
    public CategoryResponseDTO toDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setCategoryId( category.getCategoryId() );
        categoryResponseDTO.setCategoryName( category.getCategoryName() );
        if ( category.getCategoryType() != null ) {
            categoryResponseDTO.setCategoryType( category.getCategoryType().name() );
        }

        return categoryResponseDTO;
    }
}
