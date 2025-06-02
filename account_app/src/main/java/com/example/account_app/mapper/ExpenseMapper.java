package com.example.account_app.mapper;

import com.example.account_app.dto.ExpenseDTO;
import com.example.account_app.model.Category;
import com.example.account_app.model.Expense;
import com.example.account_app.model.User;

public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setDescr(expense.getDescr());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        if (expense.getCategory() != null) {
            dto.setCategoryId(expense.getCategory().getId());
            dto.setCategoryName(expense.getCategory().getName());
        }
        return dto;
    }

    public static Expense toEntity(ExpenseDTO dto, User user, Category category) {
        Expense expense = new Expense();
        expense.setId(dto.getId());
        expense.setUser(user); // текущий пользователь
        expense.setDescr(dto.getDescr());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setCategory(category);
        return expense;
    }
}
