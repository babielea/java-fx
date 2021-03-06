package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class User {

    private Username username;
    private Password password;

    public User(String username, String password) {
        this.username = new Username(username);
        this.password = new Password(password);
    }

    public String getUsernameRaw() {
        return username.getUsernameValue();
    }


    public String getPasswordRaw() {
        return password.getPasswordValue();
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
