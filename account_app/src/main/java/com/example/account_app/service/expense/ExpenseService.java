package com.example.account_app.service.expense;


import com.example.account_app.model.Expense;
import com.example.account_app.model.User;
import com.example.account_app.repository.ExpenseRepository;
import com.example.account_app.repository.UserRepository;

import com.example.account_app.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void createExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public Optional<Expense> getExpenseById(int id) {
        return expenseRepository.findById(id);
    }

    public void deleteExpense(int id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense with id " + id + " not found"));
        expenseRepository.delete(expense);
    }

    public List<Expense> getExpensesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + " not found"));
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpensesForCurrentUser() {
        // ТУТ ТЫ БУДЕШЬ ДОСТАВАТЬ ИД ПОЛЬЗОВАТЕЛЯ ИЗ SecurityContext
        int currentUserId = SecurityUtils.getCurrentUserId(); // предположим, ты сделаешь такой хелпер
        return getExpensesByUserId(currentUserId);
    }
}
