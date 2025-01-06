package com.example.Repository;

import com.example.entities.Category;
import com.example.entities.Enums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryType(Enums.CategoryType categoryType); // "income" or "expense"

    boolean existsByCategoryName(String categoryName);
}
