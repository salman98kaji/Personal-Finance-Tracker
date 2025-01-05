package com.example.Service;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.Repository.CategoryRepository;
import com.example.entities.Category;
import com.example.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

//    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
//        this.categoryRepository = categoryRepository;
//        this.categoryMapper = categoryMapper;
//    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        if(categoryRepository.existsByCategoryName(categoryRequestDTO.getCategoryName())) {
            throw new RuntimeException("Category name already exists");
        }
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesByType(String categoryType) {
        return categoryRepository.findByCategoryType(categoryType)
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
