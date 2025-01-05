package com.example.Controller;

import com.example.DTO.UserRequestDTO;
import com.example.DTO.UserResponseDTO;
import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponse = userService.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId){
        UserResponseDTO userResponse = userService.getUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        UserResponseDTO userResponse = userService.getUserByUsername(username);
        return ResponseEntity.ok(userResponse);
    }
}
