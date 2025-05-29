package com.example.account_app.service;

import com.example.account_app.model.User;
import io.ebean.Database;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void createUser(String username,String login,String password,Integer initialMoney) {
        User user = new User();
        user.setName(username);
        user.setLogin(login);
        user.setPassword(password); // в реальной жизни шифруем!

        database.save(user);
    }

    public List<User> listUsers() {
        return database.find(User.class).findList();
    }
}

