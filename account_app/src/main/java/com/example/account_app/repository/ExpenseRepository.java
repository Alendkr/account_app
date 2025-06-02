package com.example.account_app.repository;

import com.example.account_app.model.Expense;
import com.example.account_app.model.User;
import io.ebean.DB;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseRepository {

    public List<Expense> findAll() {
        return DB.find(Expense.class).findList();
    }

    public List<Expense> findByUser(User user) {
        return DB.find(Expense.class)
                .where()
                .eq("user.id", user.getId())
                .findList();
    }

    public Optional<Expense> findById(int id) {
        return Optional.ofNullable(DB.find(Expense.class).where().idEq(id).findOne());
    }

    public void save(Expense expense) {
        expense.save();
    }

    public void delete(Expense expense) {
        expense.delete();
    }
}
