package com.example.Service;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.Repository.BudgetRepository;
import com.example.Repository.CategoryRepository;
import com.example.entities.Budget;
import com.example.mapper.BudgetMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final BudgetMapper budgetMapper;

    public BudgetServiceImpl(BudgetRepository budgetRepository, CategoryRepository categoryRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.budgetMapper = budgetMapper;
    }

    @Override
    public BudgetResponseDTO createBudget(BudgetRequestDTO budgetRequestDTO) {
        Budget budget = budgetMapper.toEntity(budgetRequestDTO);
        budget.setCategory(categoryRepository.findById(budgetRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDTO(savedBudget);
    }

    @Override
    public List<BudgetResponseDTO> getBudgetsByCategory(Long categoryId) {
        return budgetRepository.findByCategoryId(categoryId)
                .stream()
                .map(budgetMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BudgetResponseDTO> getBudgetsWithinDateRange(String startDate, String endDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            return budgetRepository.findByStartDateBeforeAndEndDateAfter(start, end)
                    .stream()
                    .map(budgetMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd", e);
        }
    }
}
