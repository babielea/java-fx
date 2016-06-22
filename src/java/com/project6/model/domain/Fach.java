package com.project6.model.domain;

/**
 * Created by sulik.fabian on 22.06.2016.
 */
public class Fach {

    private String fach;
    private int lfNummer;
    private String lfName;

    public Fach(String fach, int lfNummer, String lfName) {
        this.fach = fach;
        this.lfNummer = lfNummer;
        this.lfName = lfName;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public int getLfNummer() {
        return lfNummer;
    }

    public void setLfNummer(int lfNummer) {
        this.lfNummer = lfNummer;
    }

    public String getLfName() {
        return lfName;
    }

    public void setLfName(String lfName) {
        this.lfName = lfName;
    }
}
