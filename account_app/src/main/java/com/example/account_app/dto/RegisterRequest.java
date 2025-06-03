package com.example.account_app.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String name;
}
