package com.example.Service;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;
import com.example.Repository.AccountRepository;
import com.example.Repository.UserRepository;
import com.example.entities.Account;
import com.example.entities.User;
import com.example.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AccountResponseDTO createAccount(String authenticatedUsername, AccountRequestDTO accountRequestDTO) {
        //fetch the user by username
        User user = userRepository.findByUsername(authenticatedUsername);

        //map the request DTO to the Account entity
        Account account = accountMapper.toEntity(accountRequestDTO);

        // Associate the account with the authenticated user
        account.setUser(user);

        // Save the account to the repository
        Account savedAccount = accountRepository.save(account);

        // Map the saved entity to the response DTO and return it
        return accountMapper.toDTO(savedAccount);
    }

    @Transactional
    @Override
    public List<AccountResponseDTO> getAccountsByAuthenticatedUsername(String username) {
        //fetch the user by username
        User user = userRepository.findByUsername(username);

        // Fetch accounts associated with the user
        List<Account> accounts = accountRepository.findByUser(user);
        return accounts.stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<AccountResponseDTO> getAccountsByAuthentica(Long userId) {
//        if(!userRepository.existsById(userId)) {
//            throw new RuntimeException("User not found");
//        }
//        return accountRepository.findByUser_UserId(userId)
//                .stream()
//                .map(accountMapper::toDTO)
//                .collect(Collectors.toList());
//    }
}
