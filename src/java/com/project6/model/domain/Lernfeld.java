package com.project6.model.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sulik.fabian on 22.06.2016.
 */
public class Lernfeld {

    List<Lernsituation> lsList;
    private String preName;
    private int lfNR;
    private String bezeichnung;
    private int dauer;
    private int start;
    private int ende;

    public Lernfeld(List<Lernsituation> lsList, String preName, int lfNR, String bezeichnung, int dauer, int start, int ende) {
        this.lsList = lsList;
        this.preName = preName;
        this.lfNR = lfNR;
        this.bezeichnung = bezeichnung;
        this.dauer = dauer;
        this.start = start;
        this.ende = ende;
    }

    public List<Lernsituation> getLsList() {
        return lsList;
    }

    public void setLsList(List<Lernsituation> lsList) {
        this.lsList = lsList;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public int getLfNR() {
        return lfNR;
    }

    public void setLfNR(int lfNR) {
        this.lfNR = lfNR;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnde() {
        return ende;
    }

    public void setEnde(int ende) {
        this.ende = ende;
    }
}
