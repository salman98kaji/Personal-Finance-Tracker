package com.example.Service;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.Repository.CategoryRepository;
import com.example.Repository.UserRepository;
import com.example.entities.Category;
import com.example.entities.Enums;
import com.example.entities.User;
import com.example.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(String username, CategoryRequestDTO categoryRequestDTO) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + username);
        }

        Category category = categoryMapper.toEntity(categoryRequestDTO);

        category.setUser(user);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategoriesByUser(String username) {
        List<Category> categories = categoryRepository.findByUserUsername(username);
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponseDTO> getCategoriesByType(Enums.CategoryType categoryType) {
        return categoryRepository.findByCategoryType(Enums.CategoryType.valueOf(String.valueOf(categoryType)))
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
