package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class Password {

    private String passwordValue;

    public Password(String passwordValue) {
        this.passwordValue = passwordValue;
    }

    public String getPasswordValue() {
        return passwordValue;
    }

    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }
}
