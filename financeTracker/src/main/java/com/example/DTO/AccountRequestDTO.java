package com.example.DTO;

import com.example.entities.Enums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotBlank
    private String accountName;

    @NotNull
    @Schema(description = "The type of the account")
    private Enums.AccountType accountType; // ENUM: "savings", "checking", "credit", "cash"

    @NotNull
    private double balance;
}
