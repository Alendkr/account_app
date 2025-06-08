package com.example.account_app.controller;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.service.ExpenseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/me")
    public List<ExpenseDTO> getMyExpenses() {
        log.info("Fetching expenses for current user");
        List<ExpenseDTO> expenses = expenseService.getExpenseDTOsForCurrentUser();
        log.info("Found {} expenses", expenses.size());
        return expenses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable int id) {
        log.info("Fetching expense by id: {}", id);
        return expenseService.getExpenseById(id)
                .map(dto -> {
                    log.info("Expense found: {}", dto);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> {
                    log.warn("Expense not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody ExpenseDTO dto) {
        try {
            log.info("Creating new expense: {}", dto);
            expenseService.createExpense(dto);
            log.info("Expense created successfully");
            return ResponseEntity.ok("Expense created successfully");
        } catch (RuntimeException e) {
            log.error("Error creating expense: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id) {
        try {
            log.info("Deleting expense with id: {}", id);
            expenseService.deleteExpense(id);
            log.info("Expense deleted successfully");
            return ResponseEntity.ok("Expense deleted successfully");
        } catch (RuntimeException e) {
            log.error("Error deleting expense: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
