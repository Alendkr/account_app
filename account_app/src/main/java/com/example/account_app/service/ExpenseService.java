package com.example.account_app.service;

import com.example.account_app.model.Expense;
import io.ebean.DB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    public List<Expense> getAll() {
        return DB.find(Expense.class).findList();
    }

    public Optional<Expense> getById(Integer id) {
        Expense expense = DB.find(Expense.class, id);
        return Optional.ofNullable(expense);
    }

    public Expense save(Expense expense) {
        expense.save();
        return expense;
    }

    public void delete(Integer id) {
        DB.delete(Expense.class, id);
    }
}