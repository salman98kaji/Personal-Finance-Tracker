package com.example.mapper;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;
import com.example.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequestDTO dto);

    @Mapping(source = "user.username", target = "userName", ignore = true) // Add if needed to expose related user info
    AccountResponseDTO toDTO(Account account);
}
