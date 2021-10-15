package com.example.emp.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(String login) {
        this.message = "Nie znaleziono użytkownika o loginie: " + login;
    }
}
