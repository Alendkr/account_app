package com.example.account_app.repository;

import com.example.account_app.model.User;
import io.ebean.DB;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public List<User> findAll() {
        return DB.find(User.class).findList();
    }

    public Optional<User> findById(Integer id) {
        User user = DB.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByLogin(String login) {
        // Реализация поиска по логину через Ebean:
        User user = DB.find(User.class)
                .where()
                .eq("login", login)
                .findOne();
        return Optional.ofNullable(user);
    }

    public User save(User user) {
        user.save();
        return user;
    }

    public void deleteById(Integer id) {
        DB.delete(User.class, id);
    }
}
