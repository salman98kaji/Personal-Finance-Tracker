package com.example.Repository;

import com.example.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryType(String categoryType); // "income" or "expense"

    boolean existsByCategoryName(String categoryName);
}
