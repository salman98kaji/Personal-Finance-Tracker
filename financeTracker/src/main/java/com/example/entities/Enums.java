package com.example.entities;

import io.swagger.v3.oas.annotations.media.Schema;

public class Enums {

    @Schema(description = "Different types of accounts")
    public enum AccountType {
        @Schema(description = "Savings account")
        SAVINGS,

        @Schema(description = "Checking account")
        CHECKING,

        @Schema(description = "Credit account")
        CREDIT,

        @Schema(description = "Cash account")
        CASH
    }

    @Schema(description = "Different types of categories")
    public enum CategoryType {
        @Schema(description = "Income category")
        INCOME,

        @Schema(description = "Expense category")
        EXPENSE
    }

    @Schema(description = "Different types of transactions")
    public enum TransactionType {
        @Schema(description = "Income transaction")
        INCOME,

        @Schema(description = "Expense transaction")
        EXPENSE
    }
}
