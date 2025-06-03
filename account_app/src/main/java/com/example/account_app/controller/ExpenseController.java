package com.example.account_app.controller;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.mapper.ExpenseMapper;
import com.example.account_app.model.Expense;
import com.example.account_app.service.expense.ExpenseService;
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

    // Возвращаем DTO, чтобы убрать ненужные поля
    @GetMapping("/me")
    public List<ExpenseDTO> getMyExpenses() {
        return expenseService.getExpenseDTOsForCurrentUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable int id) {
        Expense expense = expenseService.getExpenseById(id)
                .orElseThrow(() -> new RuntimeException("Расход не найден"));
        ExpenseDTO dto = ExpenseMapper.toDTO(expense);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody Expense expense) {
        try {
            expenseService.createExpense(expense);
            return ResponseEntity.ok("Expense created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok("Expense deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
