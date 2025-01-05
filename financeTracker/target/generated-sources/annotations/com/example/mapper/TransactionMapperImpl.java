package com.example.mapper;

import com.example.DTO.TransactionRequestDTO;
import com.example.DTO.TransactionResponseDTO;
import com.example.entities.Account;
import com.example.entities.Category;
import com.example.entities.Transaction;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-05T17:20:48+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl implements TransactionMapper {

    @Override
    public Transaction toEntity(TransactionRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionType( dto.getTransactionType() );
        transaction.setAmount( dto.getAmount() );
        transaction.setTransactionDate( dto.getTransactionDate() );
        transaction.setDescription( dto.getDescription() );

        return transaction;
    }

    @Override
    public TransactionResponseDTO toDTO(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();

        transactionResponseDTO.setAccountName( transactionAccountAccountName( transaction ) );
        transactionResponseDTO.setCategoryName( transactionCategoryCategoryName( transaction ) );
        transactionResponseDTO.setTransactionId( transaction.getTransactionId() );
        if ( transaction.getTransactionType() != null ) {
            transactionResponseDTO.setTransactionType( transaction.getTransactionType().name() );
        }
        transactionResponseDTO.setAmount( transaction.getAmount() );
        transactionResponseDTO.setTransactionDate( transaction.getTransactionDate() );
        transactionResponseDTO.setDescription( transaction.getDescription() );

        return transactionResponseDTO;
    }

    private String transactionAccountAccountName(Transaction transaction) {
        Account account = transaction.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getAccountName();
    }

    private String transactionCategoryCategoryName(Transaction transaction) {
        Category category = transaction.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getCategoryName();
    }
}
