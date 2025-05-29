package com.example.account_app.dto;

public class UserDTO {
    private Integer id;
    private String name;
    private String login;
    private Integer money;

    public UserDTO() {}

    public UserDTO(Integer id, String name, String login, Integer money) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.money = money;
    }

    // Getters и Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
