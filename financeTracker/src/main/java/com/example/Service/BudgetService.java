package com.example.Service;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;

import java.util.List;

public interface BudgetService {
    BudgetResponseDTO createBudget(BudgetRequestDTO budgetRequestDTO, String userName);

    List<BudgetResponseDTO> getAllBudgetsForUser(String username);

    List<BudgetResponseDTO> getBudgetsWithinDateRange(String startDate, String endDate);
}
