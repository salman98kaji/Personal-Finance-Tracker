package com.example.DTO;

import lombok.Data;

@Data
public class AccountResponseDTO {
    private Long accountId;
    private String accountName;
    private String accountType;
    private double balance;
}
