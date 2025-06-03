package com.example.account_app.repository;

import com.example.account_app.model.Receipt;
import com.example.account_app.model.User;
import io.ebean.DB;
import io.ebean.Database;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReceiptRepository {

    private final Database database;

    public ReceiptRepository() {
        this.database = DB.getDefault();
    }

    public List<Receipt> findAll() {
        return database.find(Receipt.class).findList();
    }

    public Optional<Receipt> findById(int id) {
        Receipt receipt = database.find(Receipt.class, id);
        return Optional.ofNullable(receipt);
    }

    public List<Receipt> findByUser(User user) {
        return database.find(Receipt.class)
                .where()
                .eq("user.id", user.getId())
                .findList();
    }

    public Receipt save(Receipt receipt) {
        if (receipt.getId() == null) {
            database.save(receipt);
        } else {
            database.update(receipt);
        }
        return receipt;
    }

    public void deleteById(int id) {
        database.delete(Receipt.class, id);
    }

    public void delete(Receipt receipt) {
        database.delete(receipt);
    }
}
