package com.project6.model.domain;

import javax.print.DocFlavor;
import java.util.Date;

/**
 * Created by rottsik on 22.06.2016.
 */
public class Lernsituation {

    private String name;
    private int lernstationsNo;
    private int uStunden;
    private int von;
    private int bis;
    private String szenario;
    private String handlungsprodukt;
    private String kompetenzen;
    private String inhalt;
    private String uMaterial;
    private String organisation;
    private String ersteller;
    private Date creted;

    public Lernsituation(String name,
                         int lernstationsNo,
                         int uStunden,
                         int von,
                         int bis,
                         String szenario,
                         String handlungsprodukt,
                         String kompetenzen,
                         String inhalt,
                         String uMaterial,
                         String organisation,
                         String ersteller,
                         Date creted) {
        this.name = name;
        this.lernstationsNo = lernstationsNo;
        this.uStunden = uStunden;
        this.von = von;
        this.bis = bis;
        this.szenario = szenario;
        this.handlungsprodukt = handlungsprodukt;
        this.kompetenzen = kompetenzen;
        this.inhalt = inhalt;
        this.uMaterial = uMaterial;
        this.organisation = organisation;
        this.ersteller = ersteller;
        this.creted = creted;
    }

    public String getName() {
        return name;
    }

    public int getLernstationsNo() {
        return lernstationsNo;
    }

    public int getuStunden() {
        return uStunden;
    }

    public int getVon() {
        return von;
    }

    public int getBis() {
        return bis;
    }

    public String getSzenario() {
        return szenario;
    }

    public String getHandlungsprodukt() {
        return handlungsprodukt;
    }

    public String getKompetenzen() {
        return kompetenzen;
    }

    public String getInhalt() {
        return inhalt;
    }

    public String getuMaterial() {
        return uMaterial;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getErsteller() {
        return ersteller;
    }

    public Date getCreted() {
        return creted;
    }
}
