package com.example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionRequestDTO {
    @NotNull
    private Long accountId;

    @NotNull
    private Long categoryId;

    @NotBlank
    private String transactionType; // ENUM: "income", "expense"

    @NotNull
    private double amount;

    @NotNull
    private Date transactionDate;

    private String description;
}
