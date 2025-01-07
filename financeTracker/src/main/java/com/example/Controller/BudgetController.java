package com.example.Controller;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.Service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetResponseDTO> addBudget(@RequestBody BudgetRequestDTO budgetRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        BudgetResponseDTO budgetResponse = budgetService.createBudget(budgetRequestDTO, userName);
        return ResponseEntity.ok(budgetResponse);
    }

//    @GetMapping
//    public ResponseEntity<List<BudgetResponseDTO>> getBudgetByCategory(@PathVariable Long categoryId){
//        List<BudgetResponseDTO> budgets = budgetService.getBudgetsByCategory(categoryId);
//        return ResponseEntity.ok(budgets);
//    }
    @GetMapping
    public ResponseEntity<List<BudgetResponseDTO>> getAllBudgetsForUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        List<BudgetResponseDTO> budgets = budgetService.getAllBudgetsForUser(userName);
        return ResponseEntity.ok(budgets);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<BudgetResponseDTO>> getBudgetsWithDateRange(@RequestParam String startDate, @RequestParam String endDate){
        List<BudgetResponseDTO> budgets = budgetService.getBudgetsWithinDateRange(startDate, endDate);
        return ResponseEntity.ok(budgets);
    }
}
