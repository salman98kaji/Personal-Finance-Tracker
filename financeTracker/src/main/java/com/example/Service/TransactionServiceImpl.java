package com.example.Service;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;
import com.example.Repository.AccountRepository;
import com.example.Repository.CategoryRepository;
import com.example.Repository.TransactionRepository;
import com.example.entities.Transaction;
import com.example.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository,
                                  CategoryRepository categoryRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponseDTO addTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionRequestDTO);
        transaction.setAccount(accountRepository.findById(transactionRequestDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found")));
        transaction.setCategory(categoryRepository.findById(transactionRequestDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toDTO(savedTransaction);
    }

    @Override
    public List<TransactionResponseDTO> getTransactionByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
