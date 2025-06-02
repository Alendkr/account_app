package com.example.account_app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private int id;
    private String descr;
    private int amount;
    private LocalDate expenseDate;
    private Integer categoryId;
    private String categoryName;

    public ExpenseDTO() {
        // пустой конструктор
    }

    public ExpenseDTO(int id, String descr, int amount, LocalDate expenseDate, Integer categoryId, String categoryName) {
        this.id = id;
        this.descr = descr;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
