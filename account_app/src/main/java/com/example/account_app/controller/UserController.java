package com.example.account_app.controller;

import com.example.account_app.dto.RegisterRequest;
import com.example.account_app.dto.UserDTO;
import com.example.account_app.mapper.UserMapper;
import com.example.account_app.model.User;
import com.example.account_app.service.user.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, @RequestParam String password) {
        if (userService.existsByLogin(userDTO.getLogin())) {
            return ResponseEntity.badRequest().body("Логин уже занят");
        }
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(password); // чистый пароль, не хэшируем здесь!
        User saved = userService.saveUser(user); // хэширование в сервисе
        return ResponseEntity.ok(UserMapper.toDTO(saved));
    }



    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
