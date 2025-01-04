package com.example.Controller;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;
import com.example.Service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        TransactionResponseDTO transactionResponse = transactionService.addTransaction(transactionRequestDTO);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/account/{accountId}")
    public  ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccountId(@PathVariable Long accountId){
        List<TransactionResponseDTO> transactions = transactionService.getTransactionByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }
}
