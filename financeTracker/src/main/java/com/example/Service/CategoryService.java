package com.example.Service;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.entities.Enums;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(String username, CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategoriesByUser(String username);
    List<CategoryResponseDTO> getCategoriesByType(Enums.CategoryType categoryType);
}
