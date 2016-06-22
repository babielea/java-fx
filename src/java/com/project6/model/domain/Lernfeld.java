package com.project6.model.domain;

import java.util.List;

/**
 * Lernfeld
 * Created by rottsik on 22.06.2016.
 */
public class Lernfeld {

    /**
     * Lernsituationen eines Lernfelds
     */
    private List<Lernsituation> lernsituationen;

    /**
     * Bezeichnung
     */
    private String description;

    /**
     * Namedefault
     */
    private String nameDefault;

    /**
     * Lernfeldnummer
     */
    private int lernfeldNumber;

    /**
     * Dauer des Lernfelds in UStunden
     */
    private int lernfeldDuration;

    /**
     * Beginn in Unterrichtswochen
     */
    private int start;

    /**
     * Ende in Unterrichtswochen
     */
    private int end;

    /**
     * Fach
     */
    private String fach;

    /**
     * LernfeldID
     */
    private int lernfeldID;

    public Lernfeld(List<Lernsituation> lernsituationen,
                    String description,
                    String nameDefault,
                    int lernfeldNumber,
                    int lernfeldDuration,
                    int start,
                    int end,
                    String fach,
                    int lernfeldID) {
        this.lernsituationen = lernsituationen;
        this.description = description;
        this.nameDefault = nameDefault;
        this.lernfeldNumber = lernfeldNumber;
        this.lernfeldDuration = lernfeldDuration;
        this.start = start;
        this.end = end;
        this.fach = fach;
        this.lernfeldID = lernfeldID;
    }

    public List<Lernsituation> getLernsituationen() {
        return lernsituationen;
    }

    public String getDescription() {
        return description;
    }

    public String getNameDefault() {
        return nameDefault;
    }

    public int getLernfeldNumber() {
        return lernfeldNumber;
    }

    public int getLernfeldDuration() {
        return lernfeldDuration;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getFach() {
        return fach;
    }
}
