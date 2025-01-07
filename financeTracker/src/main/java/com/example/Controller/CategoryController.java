package com.example.Controller;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.Service.CategoryService;
import com.example.entities.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        CategoryResponseDTO categoryResponse = categoryService.createCategory(username, categoryRequestDTO);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<CategoryResponseDTO> categories = categoryService.getAllCategoriesByUser(username);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/type/{categoryType}")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategoriesByType(@PathVariable Enums.CategoryType categoryType){
        List<CategoryResponseDTO> categories = categoryService.getCategoriesByType(categoryType);
        return ResponseEntity.ok(categories);
    }
}
