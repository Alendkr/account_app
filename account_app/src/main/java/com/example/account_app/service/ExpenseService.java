package com.example.account_app.service;

import com.example.account_app.model.Expense;
import com.example.account_app.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id);
    }

    public void createExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public void deleteExpense(int id) {
        Expense expense = expenseRepository.findById(id);
        if (expense != null) {
            expenseRepository.delete(expense);
        }
    }
}
