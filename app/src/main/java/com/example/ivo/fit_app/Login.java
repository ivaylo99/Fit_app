package com.example.ivo.fit_app;

/**
 * Created by Ivo on 12.2.2018 г..
 */

public class Login {
    public String name;
    public String password;

    public Login() {}

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
