package com.example.Service;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    TransactionResponseDTO addTransaction(TransactionRequestDTO transactionRequestDTO);
    List<TransactionResponseDTO> getTransactionByAccountId(Long accountId);
}
