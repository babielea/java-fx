package com.project6.model.domain;

/**
 * Created by RottsiK on 22.06.2016.
 */
public class DataForCover {

    private int yearOfAppentice;
    private String apprenticeJob;
    private String bildungsgangsleitung;
    private String bereichsleitung;

    public DataForCover(int yearOfAppentice, String apprenticeJob, String bildungsgangsleitung, String bereichsleitung) {
        this.yearOfAppentice = yearOfAppentice;
        this.apprenticeJob = apprenticeJob;
        this.bildungsgangsleitung = bildungsgangsleitung;
        this.bereichsleitung = bereichsleitung;
    }

    public int getYearOfAppentice() {
        return yearOfAppentice;
    }

    public String getApprenticeJob() {
        return apprenticeJob;
    }

    public String getBildungsgangsleitung() {
        return bildungsgangsleitung;
    }

    public String getBereichsleitung() {
        return bereichsleitung;
    }
}
