package com.example.Controller;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;
import com.example.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

//    public TransactionController(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> addTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        TransactionResponseDTO transactionResponse = transactionService.addTransaction(username, transactionRequestDTO);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping("/account/{accountId}")
    public  ResponseEntity<List<TransactionResponseDTO>> getTransactionsByAccountId(@PathVariable Long accountId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactionsByAccount(username, accountId);
        return ResponseEntity.ok(transactions);
    }
}
