package com.example.Service;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;
import com.example.Repository.AccountRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.TransactionRepository;
import com.example.Repository.UserRepository;
import com.example.customException.AccountNotFoundException;
import com.example.customException.CategoryNotFoundException;
import com.example.entities.Account;
import com.example.entities.Category;
import com.example.entities.Transaction;
import com.example.entities.User;
import com.example.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public TransactionResponseDTO addTransaction(String username, TransactionRequestDTO transactionRequestDTO) {

        // Fetch the account by ID and validate user ownership
        Account account = accountRepository.findById(transactionRequestDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + transactionRequestDTO.getAccountId()));

        if(!account.getUser().getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to access this account");
        }

        // Fetch the category by ID
        Category category = categoryRepository.findById(transactionRequestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for ID: " + transactionRequestDTO.getCategoryId()));

        // Map the request DTO to the entity
        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);

        // Set account and category for the transaction
        transaction.setAccount(account);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Transactional
    @Override
    public List<TransactionResponseDTO> getAllTransactionsByAccount(String username, Long accountId) {
        // Fetch the account and validate ownership
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + accountId));

        if(!account.getUser().getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to access this account");
        }

        // Fetch and return transactions for the account
        return transactionRepository.findByAccount_AccountId(accountId)
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

}
