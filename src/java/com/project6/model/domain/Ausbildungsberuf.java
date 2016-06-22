package com.project6.model.domain;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class Ausbildungsberuf  {

    private String beruf;
    private String bildungsgangsleiter;

    public Ausbildungsberuf(String ausbildungsberuf, String leiter) {
        this.beruf = ausbildungsberuf;
        this.bildungsgangsleiter = leiter;
    }

    public String getBeruf() {
        return beruf;
    }

    public void setBeruf(String beruf) {
        this.beruf = beruf;
    }

    public String getBildungsgangsleiter() {
        return bildungsgangsleiter;
    }

    public void setBildungsgangsleiter(String bildungsgangsleiter) {
        this.bildungsgangsleiter = bildungsgangsleiter;
    }
}
