package com.example.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BudgetResponseDTO {
    private Long budgetId;
    private String categoryName;
    private double budgetAmount;
    private Date startDate;
    private Date endDate;
}
