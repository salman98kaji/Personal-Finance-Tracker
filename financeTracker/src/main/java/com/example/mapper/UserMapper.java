package com.example.mapper;

import com.example.DTO.UserRequestDTO;
import com.example.DTO.UserResponseDTO;
import com.example.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "passwordHash", ignore = true)
    User toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(User user);
}
