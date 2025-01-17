package com.example.Repository;

import com.example.entities.Account;
import com.example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUser(User user);
    Optional<Account> findByAccountIdAndUser(Long accountId, User user);

}
