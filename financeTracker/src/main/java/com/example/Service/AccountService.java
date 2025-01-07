package com.example.Service;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    AccountResponseDTO createAccount(String authenticatedUsername, AccountRequestDTO accountRequestDTO);
    List<AccountResponseDTO> getAccountsByAuthenticatedUsername(String username);
}
