package com.example.account_app.controller;

import com.example.account_app.model.Expense;
import com.example.account_app.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable int id) {
        Expense expense = expenseService.getExpenseById(id);
        return (expense != null) ? ResponseEntity.ok(expense) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody Expense expense) {
        expenseService.createExpense(expense);
        return ResponseEntity.ok("Expense created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense deleted successfully");
    }
}
