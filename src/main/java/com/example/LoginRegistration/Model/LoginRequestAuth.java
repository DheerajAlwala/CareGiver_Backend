package com.example.LoginRegistration.Model;

public class LoginRequestAuth {
    String username;
    String password;

    public LoginRequestAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequestAuth() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
