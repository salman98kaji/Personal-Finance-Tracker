package com.example.mapper;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;
import com.example.entities.Account;
import com.example.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-04T14:47:41+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(AccountRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Account account = new Account();

        account.setAccountName( dto.getAccountName() );
        account.setAccountType( dto.getAccountType() );
        account.setBalance( dto.getBalance() );

        return account;
    }

    @Override
    public AccountResponseDTO toDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setUserName( accountUserUsername( account ) );
        accountResponseDTO.setAccountId( account.getAccountId() );
        accountResponseDTO.setAccountName( account.getAccountName() );
        if ( account.getAccountType() != null ) {
            accountResponseDTO.setAccountType( account.getAccountType().name() );
        }
        accountResponseDTO.setBalance( account.getBalance() );

        return accountResponseDTO;
    }

    private String accountUserUsername(Account account) {
        User user = account.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getUsername();
    }
}
