package com.example.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionResponseDTO {
    private Long transactionId;
    private String accountName;
    private String categoryName;
    private String transactionType;
    private double amount;
    private Date transactionDate;
    private String description;
}
