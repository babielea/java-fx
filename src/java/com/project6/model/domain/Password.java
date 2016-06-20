package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class Password {

    private String rawValue;
    private String cleanValue;

    public Password(String rawValue) throws InvalidFormatException{
        this.rawValue = rawValue;
        this.cleanValue = assertValue();
    }

    private String assertValue() throws InvalidFormatException {
        if(rawValue.isEmpty()) throw new InvalidFormatException("Password darf nicht leer sein");
        return rawValue;
    }

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getCleanValue() {
        return cleanValue;
    }
}
