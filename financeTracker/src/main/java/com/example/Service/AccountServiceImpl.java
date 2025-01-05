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


//    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper){
//        this.accountRepository = accountRepository;
//        this.accountMapper = accountMapper;
//        this.userRepository = userRepository;
//    }

    @Override
    public AccountResponseDTO createAccount(Long userId, AccountRequestDTO accountRequestDTO) {
        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("user not found");
        }

        Account account = accountMapper.toEntity(accountRequestDTO);
        account.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toDTO(savedAccount);
    }

    @Override
    public List<AccountResponseDTO> getAccountsByUserId(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return accountRepository.findByUser_UserId(userId)
                .stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }
}
