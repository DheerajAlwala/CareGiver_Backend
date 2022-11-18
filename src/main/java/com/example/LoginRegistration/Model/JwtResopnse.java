package com.example.LoginRegistration.Model;

import java.io.Serializable;

public class JwtResopnse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;

    public JwtResopnse() {
    }

    public JwtResopnse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
