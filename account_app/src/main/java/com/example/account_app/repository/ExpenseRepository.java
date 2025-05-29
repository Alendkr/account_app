package com.example.account_app.repository;

import com.example.account_app.model.Expense;
import io.ebean.DB;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseRepository {

    public List<Expense> findAll() {
        return DB.find(Expense.class).findList();
    }

    public Expense findById(int id) {
        return DB.find(Expense.class).where().idEq(id).findOne();
    }

    public void save(Expense expense) {
        expense.save();
    }

    public void delete(Expense expense) {
        expense.delete();
    }
}
