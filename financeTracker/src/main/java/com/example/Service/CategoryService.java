package com.example.Service;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.entities.Enums;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategories();
    List<CategoryResponseDTO> getCategoriesByType(Enums.CategoryType categoryType);
}
