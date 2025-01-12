package com.example.Service;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.Repository.BudgetRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.UserRepository;
import com.example.entities.Budget;
import com.example.entities.Category;
import com.example.entities.User;
import com.example.mapper.BudgetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public BudgetResponseDTO createBudget(BudgetRequestDTO budgetRequestDTO, String userName) {

        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found for username: " + userName);
        }

        Category category = categoryRepository.findById(budgetRequestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found for id: "+budgetRequestDTO.getCategoryId()));

        //Map the DTO to the Budget entity
        Budget budget = budgetMapper.toEntity(budgetRequestDTO);

        // Set the User and Category in the Budget entity
        budget.setUser(user);
        budget.setCategory(category);

        //Save the Budget entity and return the response DTO
        Budget savedBudget = budgetRepository.save(budget);

        return budgetMapper.toDTO(savedBudget);
    }

    @Override
    @Transactional
    public List<BudgetResponseDTO> getAllBudgetsForUser(String username) {
        List<Budget> budgets = budgetRepository.findByUserUsername(username);
        return budgets.stream()
                .map(budgetMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<BudgetResponseDTO> getBudgetsByCategory(Long categoryId) {
//        return budgetRepository.findByCategory_CategoryId(categoryId)
//                .stream()
//                .map(budgetMapper::toDTO)
//                .collect(Collectors.toList());
//    }

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
