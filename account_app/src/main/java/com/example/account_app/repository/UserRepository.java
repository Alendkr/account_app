package com.example.account_app.repository;

import com.example.account_app.model.User;
import io.ebean.DB;
import io.ebean.Database;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Database database;

    public UserRepository() {
        this.database = DB.getDefault();
    }

    public List<User> findAll() {
        return database.find(User.class).findList();
    }

    public Optional<User> findById(Integer id) {
        User user = database.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByLogin(String login) {
        User user = database.find(User.class)
                .where()
                .eq("login", login)
                .findOne();
        return Optional.ofNullable(user);
    }

    public User save(User user) {
        if (user.getId() == null) {
            database.save(user);
        } else {
            database.update(user);
        }
        return user;
    }

    // Крафтим что то на коленке, т.к работаем не через Spring Data JPA а Ebean
    public boolean existsById(Integer id) {
        int count = database.find(User.class)
                .where()
                .idEq(id)
                .findCount();
        return count > 0;
    }


    public boolean existsByLogin(String login) {
        int count = database.find(User.class)
                .where()
                .eq("login", login)  // именно тут
                .findCount();
        return count > 0;
    }


    public void deleteById(Integer id) {
        database.delete(User.class, id);
    }

    public void delete(User user) {
        database.delete(user);
    }
}
