package com.example.Repository;

import com.example.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByCategoryId(Long categoryId);

    List<Budget> findByStartDateBeforeAndEndDateAfter(Date date1, Date date2);

    boolean existsByCategoryId(Long categoryId);
}
