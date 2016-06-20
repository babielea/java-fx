package com.project6.model.domain;

/**
 * Created by SalP on 20.06.2016.
 */
public class Username {

    private String rawValue;
    private String cleanValue;

    public Username(String rawValue) throws InvalidFormatException {
        this.rawValue = rawValue;
        this.cleanValue = assertValue();
    }

    private String assertValue() throws InvalidFormatException {
        if(rawValue.isEmpty()) throw new InvalidFormatException("Name darf nicht leer sein");
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
