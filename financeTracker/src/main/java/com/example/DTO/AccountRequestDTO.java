package com.example.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {
    @NotBlank
    private String accountName;

    @NotNull
    private String accountType; // ENUM: "savings", "checking", "credit", "cash"

    @NotNull
    private double balance;
}
