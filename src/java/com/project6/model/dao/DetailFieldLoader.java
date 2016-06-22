package com.project6.model.dao;

import com.project6.database.Database;
import com.project6.model.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class DetailFieldLoader {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private Connection connection = Database.getInstance();

    public DetailFieldLoader() {
    }


    public List<Ausbildungsberuf> getBerufe() {

        List<Ausbildungsberuf> berufeResults = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT b.Berufname, l.Username FROM tbl_beruf b JOIN tbl_lehrer l ON b.ID_BLeitung = l.LId;");
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String ausbildungsberuf = resultSet.getString(1);
                String leiter = resultSet.getString(2);
                berufeResults.add(new Ausbildungsberuf(ausbildungsberuf, leiter));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return berufeResults;
    }


    public List<Abteilung> getAbteilung() {

        List<Abteilung> abteilungResults = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT a.Abteilungsname, l.Lehrername FROM tbl_abteilung a JOIN tbl_lehrer l ON a.ID_Leiter = l.LId;");
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String abteilung = resultSet.getString(1);
                String leiter = resultSet.getString(2);
                abteilungResults.add(new Abteilung(abteilung, leiter));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abteilungResults;
    }


    public List<Fach> getFach() {

        List<Fach> fachResults = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT f.Bezeichnung, lf.LFNR, lf.Bezeichnung FROM tbl_fach f JOIN tbl_lernfeld lf ON f.Lernbereich = lf.LFID;");
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String fach = resultSet.getString(1);
                int nummer = resultSet.getInt(2);
                String lernfeld = resultSet.getString(3);
                fachResults.add(new Fach(fach, nummer, lernfeld));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fachResults;
    }


    public List<Lernsituation> getLernsituation() {

        List<Lernsituation> lsResults = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT ls.LSNR, ls.Szenario, ls.Handlungsprodukt, ls.Kompetenzen, ls.Inhalte, ls.Umaterial, ls.Organisation, ls.Arbeitstechnik, ln.Art FROM tbl_lernsituation ls JOIN tbl_lernsituationleistungsnachweis lsln ON ls.LSID = lsln.id_lernsituation JOIN tbl_leistungsnachweis ln ON lsln.ID_Leistungsnachweis = ln.LNID;");
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int lsID = resultSet.getInt(1);
                String szenario = resultSet.getString(2);
                String handlungsprodukt = resultSet.getString(3);
                String kompetenzen = resultSet.getString(4);
                String inhalte = resultSet.getString(5);
                String umaterial = resultSet.getString(6);
                String organisation = resultSet.getString(7);
                String arbeitstechnik = resultSet.getString(8);
                String leistungsnachweis = resultSet.getString(9);
                lsResults.add(new Lernsituation(lsID, szenario, handlungsprodukt, kompetenzen, inhalte, umaterial, organisation, arbeitstechnik, leistungsnachweis));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lsResults;
    }

}
