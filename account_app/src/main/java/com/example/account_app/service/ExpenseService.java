package com.example.account_app.service;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.mapper.ExpenseMapper;
import com.example.account_app.model.Category;
import com.example.account_app.model.Expense;
import com.example.account_app.model.User;
import com.example.account_app.repository.CategoryRepository;
import com.example.account_app.repository.ExpenseRepository;
import com.example.account_app.repository.UserRepository;
import com.example.account_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ExpenseDTO> getExpenseDTOsForCurrentUser() {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findByUser(user).stream()
                .map(ExpenseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ExpenseDTO> getExpenseById(int id) {
        return expenseRepository.findById(id).map(ExpenseMapper::toDTO);
    }

    public void createExpense(ExpenseDTO dto) {
        int userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }

        Expense expense = ExpenseMapper.toEntity(dto, user, category);
        expenseRepository.save(expense);

        int newBalance = user.getMoney() - expense.getAmount();
        user.setMoney(newBalance);
        userRepository.save(user);

    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }
}
