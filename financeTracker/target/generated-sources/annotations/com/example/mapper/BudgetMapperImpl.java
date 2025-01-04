package com.example.mapper;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.entities.Budget;
import com.example.entities.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-04T14:30:37+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class BudgetMapperImpl implements BudgetMapper {

    @Override
    public Budget toEntity(BudgetRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Budget budget = new Budget();

        budget.setBudgetAmount( dto.getBudgetAmount() );
        budget.setStartDate( dto.getStartDate() );
        budget.setEndDate( dto.getEndDate() );

        return budget;
    }

    @Override
    public BudgetResponseDTO toDTO(Budget budget) {
        if ( budget == null ) {
            return null;
        }

        BudgetResponseDTO budgetResponseDTO = new BudgetResponseDTO();

        budgetResponseDTO.setCategoryName( budgetCategoryCategoryName( budget ) );
        budgetResponseDTO.setBudgetId( budget.getBudgetId() );
        budgetResponseDTO.setBudgetAmount( budget.getBudgetAmount() );
        budgetResponseDTO.setStartDate( budget.getStartDate() );
        budgetResponseDTO.setEndDate( budget.getEndDate() );

        return budgetResponseDTO;
    }

    private String budgetCategoryCategoryName(Budget budget) {
        Category category = budget.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getCategoryName();
    }
}
