package com.example.Service;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategories();
    List<CategoryResponseDTO> getCategoriesByType(String categoryType);
}
