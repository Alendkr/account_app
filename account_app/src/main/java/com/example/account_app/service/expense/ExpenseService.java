package com.example.account_app.service.expense;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.mapper.ExpenseMapper;
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

    public List<Expense> getExpensesForCurrentUser() {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        return expenseRepository.findByUser(user);
    }

    public List<ExpenseDTO> getExpenseDTOsForCurrentUser() {
        List<Expense> expenses = getExpensesForCurrentUser();  // твой существующий метод
        return ExpenseMapper.toDTOList(expenses);
    }

    public void createExpense(Expense expense) {
        int currentUserId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        expense.setUser(user);
        expenseRepository.save(expense);
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }

    public Optional<Expense> getExpenseById(int id) {
        return expenseRepository.findById(id);
    }
}
