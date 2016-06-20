package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class InvalidFormatException extends Exception {

    public InvalidFormatException() {
    }

    public InvalidFormatException(String message) {
        super(message);
    }
}
