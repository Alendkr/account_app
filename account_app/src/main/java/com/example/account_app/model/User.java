package com.example.account_app.model;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@ToString(exclude = "password")  // чтобы случайно не утекал пароль при логах
public class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String password;

    private Integer money = 0;

    public User(String name, String login, String password, Integer money) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.money = money;
    }
}
