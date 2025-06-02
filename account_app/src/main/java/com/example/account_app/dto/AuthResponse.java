package com.example.account_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private String login;

    public AuthResponse() {
    }

    public AuthResponse(String token, String login) {
        this.token = token;
        this.login = login;
    }
}
