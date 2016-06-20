package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class User {

    private Username username;
    private Password password;

    public User(String username, String password) {
        try {
            this.username = new Username(username);
        } catch (InvalidFormatException e) {
            System.out.println("Wrong username format: " + e.getMessage());
        }
        try {
            this.password = new Password(password);
        } catch (InvalidFormatException e) {
            System.out.println("Wrong password format: " + e.getMessage());
        }
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
