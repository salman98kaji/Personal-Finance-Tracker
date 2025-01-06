package com.example.Controller;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;
import com.example.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{userId}")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable Long userId, @RequestBody AccountRequestDTO accountRequestDTO) {
        AccountResponseDTO accountResponse = accountService.createAccount(userId, accountRequestDTO);
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByUserId(@PathVariable Long userId){
        List<AccountResponseDTO> accounts = accountService.getAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }
}
