package com.example.mapper;

import com.example.DTO.BudgetRequestDTO;
import com.example.DTO.BudgetResponseDTO;
import com.example.entities.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BudgetMapper {
    Budget toEntity(BudgetRequestDTO dto);

    @Mapping(source = "category.categoryName", target = "categoryName")
    BudgetResponseDTO toDTO(Budget budget);
}
