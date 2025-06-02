package com.example.account_app.controller;

import com.example.account_app.dto.UserDTO;
import com.example.account_app.mapper.UserMapper;
import com.example.account_app.model.User;
import com.example.account_app.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public List<UserDTO> getAllUsers() {
        return UserMapper.toDTOList(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    @PostMapping("/register")
    public UserDTO createUser(@RequestBody UserDTO userDTO, @RequestParam String password) {
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(password);
        User saved = userService.saveUser(user);
        return UserMapper.toDTO(saved);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
