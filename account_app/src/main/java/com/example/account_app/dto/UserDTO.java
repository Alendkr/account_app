package com.example.account_app.dto;

public class UserDTO {

    private String name;
    private String login;


    public UserDTO() {}

    public UserDTO(String name, String login) {

        this.name = name;
        this.login = login;

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

}
