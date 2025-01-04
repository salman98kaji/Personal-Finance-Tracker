package com.example.Service;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;

import java.util.List;

public interface BudgetService {
    BudgetResponseDTO createBudget(BudgetRequestDTO budgetRequestDTO);

    List<BudgetResponseDTO> getBudgetsByCategory(Long categoryId);

    List<BudgetResponseDTO> getBudgetsWithinDateRange(String startDate, String endDate);
}
