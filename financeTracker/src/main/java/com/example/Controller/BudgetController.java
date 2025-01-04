package com.example.Controller;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.Service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<BudgetResponseDTO> addBudget(@RequestBody BudgetRequestDTO budgetRequestDTO){
        BudgetResponseDTO budgetResponse = budgetService.createBudget(budgetRequestDTO);
        return ResponseEntity.ok(budgetResponse);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BudgetResponseDTO>> getBudgetByCategory(@PathVariable Long categoryId){
        List<BudgetResponseDTO> budgets = budgetService.getBudgetsByCategory(categoryId);
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<BudgetResponseDTO>> getBudgetsWithDateRange(@RequestParam String startDate, @RequestParam String endDate){
        List<BudgetResponseDTO> budgets = budgetService.getBudgetsWithinDateRange(startDate, endDate);
        return ResponseEntity.ok(budgets);
    }
}
