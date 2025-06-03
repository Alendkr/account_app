package com.example.account_app.controller;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.dto.ReceiptDTO;
import com.example.account_app.dto.RegisterRequest;
import com.example.account_app.dto.UserDTO;
import com.example.account_app.mapper.UserMapper;
import com.example.account_app.model.User;
import com.example.account_app.service.expense.ExpenseService;
import com.example.account_app.service.receipt.ReceiptService;
import com.example.account_app.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/all")
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all users");
        List<UserDTO> users = UserMapper.toDTOList(userService.getAllUsers());
        log.info("Found {} users", users.size());
        return users;
    }

    @GetMapping("/get/{id}")
    public UserDTO getUserById(@PathVariable Integer id) {
        log.info("Fetching user by id: {}", id);
        User user = userService.getUserById(id)
                .orElseThrow(() -> {
                    log.warn("User not found with id: {}", id);
                    return new RuntimeException("User not found");
                });
        log.info("User found: {}", user);
        return UserMapper.toDTO(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        log.info("Attempting to register user with login: {}", request.getLogin());
        if (userService.existsByLogin(request.getLogin())) {
            log.warn("Login already taken: {}", request.getLogin());
            return ResponseEntity.badRequest().body("Логин уже занят");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        User saved = userService.saveUser(user);
        log.info("User registered successfully: {}", saved);
        return ResponseEntity.ok(UserMapper.toDTO(saved));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        log.info("User deleted successfully");
    }
}
