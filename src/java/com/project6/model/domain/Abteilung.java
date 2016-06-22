package com.project6.model.domain;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class Abteilung {

    private String abteilungsname;
    private String abteilungsleiter;


    public Abteilung(String abteilungsname, String abteilungsleiter) {
        this.abteilungsname = abteilungsname;
        this.abteilungsleiter = abteilungsleiter;
    }

    public String getAbteilungsname() {
        return abteilungsname;
    }

    public void setAbteilungsname(String abteilungsname) {
        this.abteilungsname = abteilungsname;
    }

    public String getAbteilungsleiter() {
        return abteilungsleiter;
    }

    public void setAbteilungsleiter(String abteilungsleiter) {
        this.abteilungsleiter = abteilungsleiter;
    }
}
