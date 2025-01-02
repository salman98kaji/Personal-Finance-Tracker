package com.example.Service;

import com.example.DTO.UserRequestDTO;
import com.example.DTO.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long userId);
}
