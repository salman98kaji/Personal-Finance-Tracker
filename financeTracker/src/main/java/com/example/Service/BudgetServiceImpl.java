package com.example.Service;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.Repository.BudgetRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.UserRepository;
import com.example.entities.Budget;
import com.example.mapper.BudgetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BudgetResponseDTO createBudget(BudgetRequestDTO budgetRequestDTO) {

        //Map the DTO to the Budget entity
        Budget budget = budgetMapper.toEntity(budgetRequestDTO);

        //Set the category on the Budget entity
        budget.setCategory(categoryRepository.findById(budgetRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        //Set the user on the Budget entity
        budget.setUser(userRepository.findById(budgetRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("USer not found")));

        //Save the Budget entity and return the response DTO
        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDTO(savedBudget);
    }

    @Override
    public List<BudgetResponseDTO> getBudgetsByCategory(Long categoryId) {
        return budgetRepository.findByCategory_CategoryId(categoryId)
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
