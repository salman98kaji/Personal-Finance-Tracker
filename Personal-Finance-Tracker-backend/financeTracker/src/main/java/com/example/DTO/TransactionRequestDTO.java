package com.example.DTO;

import com.example.entities.Enums;
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
    private Enums.TransactionType transactionType; // ENUM: "income", "expense"

    @NotNull
    private double amount;

    @NotNull
    private Date transactionDate;

    private String description;
}
