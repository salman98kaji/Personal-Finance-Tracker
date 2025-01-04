package com.example.mapper;

import com.example.DTO.UserRequestDTO;
import com.example.DTO.UserResponseDTO;
import com.example.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-04T14:30:38+0530",
    comments = "version: 1.6.2, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );

        return user;
    }

    @Override
    public UserResponseDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setUserId( user.getUserId() );
        userResponseDTO.setUsername( user.getUsername() );
        userResponseDTO.setEmail( user.getEmail() );

        return userResponseDTO;
    }
}
