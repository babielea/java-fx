package com.project6.model.domain;

import javax.print.DocFlavor;

/**
 * Created by sulik.fabian on 22.06.2016.
 */
public class Lernsituation {

    private int lsID;
    private String szenario;
    private String handlungsprodukt;
    private String kompetenzen;
    private String inhalte;
    private String uMaterial;
    private String organisation;
    private String arbeitstechnik;
    private String leistungsnachweis;
    private String ersteller;
    private int von;
    private int bis;
    private int stunden;


    public Lernsituation(int lsID, String szenario, String handlungsprodukt,
                         String kompetenzen, String inhalte, String uMaterial,
                         String organisation, String arbeitstechnik,
                         String leistungsnachweis, String ersteller,
                         int von, int bis, int stunden) {
        this.lsID = lsID;
        this.szenario = szenario;
        this.handlungsprodukt = handlungsprodukt;
        this.kompetenzen = kompetenzen;
        this.inhalte = inhalte;
        this.uMaterial = uMaterial;
        this.organisation = organisation;
        this.arbeitstechnik = arbeitstechnik;
        this.leistungsnachweis = leistungsnachweis;
        this.ersteller = ersteller;
    }

    public int getLsID() {
        return lsID;
    }

    public void setLsID(int lsID) {
        this.lsID = lsID;
    }

    public String getSzenario() {
        return szenario;
    }

    public void setSzenario(String szenario) {
        this.szenario = szenario;
    }

    public String getHandlungsprodukt() {
        return handlungsprodukt;
    }

    public void setHandlungsprodukt(String handlungsprodukt) {
        this.handlungsprodukt = handlungsprodukt;
    }

    public String getKompetenzen() {
        return kompetenzen;
    }

    public void setKompetenzen(String kompetenzen) {
        this.kompetenzen = kompetenzen;
    }

    public String getInhalte() {
        return inhalte;
    }

    public void setInhalte(String inhalte) {
        this.inhalte = inhalte;
    }

    public String getuMaterial() {
        return uMaterial;
    }

    public void setuMaterial(String uMaterial) {
        this.uMaterial = uMaterial;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getArbeitstechnik() {
        return arbeitstechnik;
    }

    public void setArbeitstechnik(String arbeitstechnik) {
        this.arbeitstechnik = arbeitstechnik;
    }

    public String getLeistungsnachweis() {
        return leistungsnachweis;
    }

    public void setLeistungsnachweis(String leistungsnachweis) {
        this.leistungsnachweis = leistungsnachweis;
    }

    public String getErsteller() {
        return ersteller;
    }

    public void setErsteller(String ersteller) {
        this.ersteller = ersteller;
    }

    public int getVon() {
        return von;
    }

    public void setVon(int von) {
        this.von = von;
    }

    public int getBis() {
        return bis;
    }

    public void setBis(int bis) {
        this.bis = bis;
    }

    public int getStunden() {
        return stunden;
    }

    public void setStunden(int stunden) {
        this.stunden = stunden;
    }
}
