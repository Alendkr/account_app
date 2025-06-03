package com.example.account_app.repository;

import com.example.account_app.model.Category;
import com.example.account_app.model.User;
import io.ebean.DB;
import io.ebean.Database;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {

    private final Database database;

    public CategoryRepository() {
        this.database = DB.getDefault();
    }

    public List<Category> findAll() {
        return database.find(Category.class).findList();
    }

    public Optional<Category> findById(int id) {
        Category category = database.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    public List<Category> findByUser(User user) {
        return database.find(Category.class)
                .where()
                .eq("user.id", user.getId())
                .findList();
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            database.save(category);
        } else {
            database.update(category);
        }
        return category;
    }

    public void deleteById(int id) {
        database.delete(Category.class, id);
    }

    public void delete(Category category) {
        database.delete(category);
    }
}
