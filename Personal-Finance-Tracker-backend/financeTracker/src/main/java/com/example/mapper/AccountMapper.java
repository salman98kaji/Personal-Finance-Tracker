package com.example.mapper;

import com.example.DTO.AccountRequestDTO;
import com.example.DTO.AccountResponseDTO;
import com.example.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequestDTO dto);

    @Mapping(target = "userName", source = "user.username")
    AccountResponseDTO toDTO(Account account);
}
