package com.example.Controller;

import com.example.DTO.CategoryRequestDTO;
import com.example.DTO.CategoryResponseDTO;
import com.example.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponse = categoryService.createCategory(categoryRequestDTO);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/type/{categoryType}")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategoriesByType(@PathVariable String categoryType){
        List<CategoryResponseDTO> categories = categoryService.getCategoriesByType(categoryType);
        return ResponseEntity.ok(categories);
    }
}
