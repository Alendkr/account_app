package com.example.account_app.repository;

import com.example.account_app.model.Expense;
import com.example.account_app.model.User;
import io.ebean.DB;
import io.ebean.Database;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepository {

    private final Database database;

    public ExpenseRepository() {
        this.database = DB.getDefault();
    }

    public List<Expense> findAll() {
        return database.find(Expense.class).findList();
    }

    public Optional<Expense> findById(int id) {
        Expense expense = database.find(Expense.class, id);
        return Optional.ofNullable(expense);
    }

    public List<Expense> findByUser(User user) {
        return database.find(Expense.class)
                .where()
                .eq("user.id", user.getId())
                .findList();
    }

    public Expense save(Expense expense) {
        if (expense.getId() == null) {
            database.save(expense);
        } else {
            database.update(expense);
        }
        return expense;
    }

    public void deleteById(int id) {
        database.delete(Expense.class, id);
    }

    public void delete(Expense expense) {
        database.delete(expense);
    }
}
