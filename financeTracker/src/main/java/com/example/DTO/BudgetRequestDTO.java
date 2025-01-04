package com.example.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class BudgetRequestDTO {
    @NotNull
    private Long categoryId;

    @NotNull
    private double budgetAmount;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Long userId;

}
