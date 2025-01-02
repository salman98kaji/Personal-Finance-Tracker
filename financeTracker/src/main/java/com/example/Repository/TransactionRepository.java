package com.example.Repository;

import com.example.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);

    List<Transaction> findByCategoryId(Long categoryId);

    List<Transaction> findByTransactionDateBetween(Date startDate, Date endDate);

    List<Transaction> findByUserId(Long userId); // If user is linked to transactions
}
