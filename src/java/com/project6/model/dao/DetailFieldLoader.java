package com.project6.model.dao;

import com.project6.database.Database;
import com.project6.model.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hilfklasse zur Bearbeitung der Datenbank Operationen
 * Created by sulik.fabian on 21.06.2016.
 */
public class DetailFieldLoader {

    /**
     * Sichere PreparedStatements
     */
    private PreparedStatement preparedStatement = null;

    /**
     * Ergebnisse der Queries
     */
    private ResultSet resultSet = null;

    /**
     * Datenbankverbindung
     */
    private Connection connection = Database.getInstance();

    public DetailFieldLoader() {
    }

    public List<Ausbildungsberuf> getBerufe() {

        List<Ausbildungsberuf> berufeResults = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT b.Berufname, l.Username " +
                    "FROM tbl_beruf b JOIN tbl_lehrer l ON b.ID_BLeitung = l.LId;");
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
            preparedStatement = connection.prepareStatement("SELECT a.Abteilungsname, l.Lehrername" +
                    " FROM tbl_abteilung a " +
                    "JOIN tbl_lehrer l ON a.ID_Leiter = l.LId;");
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
            preparedStatement = connection.prepareStatement("SELECT f.Bezeichnung, lf.LFNR, lf.Bezeichnung" +
                    " FROM tbl_fach f " +
                    "JOIN tbl_lernfeld lf ON f.Lernbereich = lf.LFID;");
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

    /**
     * Liest alle Lernsituationen von einem Lernfeld aus der Datenbank aus.
     *
     * @param lfID LernfeldID
     * @return
     * @throws SQLException
     */
    public List<Lernsituation> getLernsituation(int lfID) throws SQLException {
        List<Lernsituation> lsResults = new ArrayList<>();

        String sql = "SELECT ls.Name, ls.LSNR, ls.UStunden, ls.Von, ls.Bis, ls.Szenario, ls.Handlungsprodukt, ls.Kompetenzen, ls.Inhalte, ls.Umaterial, ls.Organisation, ls.Ersteller, ls.Erstellt_am, ls.LSID " +
                "FROM tbl_lernsituation AS ls " +
                "WHERE ls.ID_Lernfeld = ? AND ls.Sichtbar = true " +
                "ORDER BY ls.LSNR;";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, lfID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            int lernstationNo = resultSet.getInt(2);
            int uStunden = resultSet.getInt(3);
            int von = resultSet.getInt(4);
            int bis = resultSet.getInt(5);
            String szenario = resultSet.getString(6);
            String handlungsprodukt = resultSet.getString(7);
            String kompetenzen = resultSet.getString(8);
            String inhalt = resultSet.getString(9);
            String uMaterial = resultSet.getString(10);
            String organisation = resultSet.getString(11);
            String ersteller = resultSet.getString(12);
            String created = resultSet.getString(13);
            int lsID = resultSet.getInt(14);
            lsResults.add(new Lernsituation(name, lernstationNo, uStunden, von, bis, szenario, handlungsprodukt, kompetenzen, inhalt, uMaterial, organisation, ersteller, created, lsID));
        }
        return lsResults;
    }

    /**
     * Liest alle nötigen Informationen für das Deckblatt aus der Datenbank aus.
     * Ausbildungsjahr, Ausbildungsberuf, Bildungsgangsleitung und Bereichsleitung
     *
     * @param yearOfApprentice Ausbildungsjahr
     * @param apprenticeJobID  Ausbildungsberuf
     * @return
     * @throws SQLException
     */
    public DataForCover getDataForCover(int yearOfApprentice, int apprenticeJobID) throws SQLException {
        DataForCover dataForCoversResult = null;

        String sql = "SELECT " +
                "b.Berufname," +
                "CONCAT(IF(l.Geschlecht = 'M', 'Herr', 'Frau'), ' ', l.Lehrername) AS Bildungsgangsleitung," +
                "CONCAT(IF(l.Geschlecht = 'M', 'Herr', 'Frau'), ' ', l2.Lehrername) AS Bereichsleitung " +
                "FROM tbl_beruf AS b " +
                "INNER JOIN tbl_lehrer AS l ON b.ID_BLeitung = l.LId " +
                "INNER JOIN tbl_abteilung AS a ON b.ID_Abteilung = a.AId " +
                "INNER JOIN tbl_lehrer AS l2 ON a.ID_Leiter = l2.LId " +
                "WHERE b.BId = ?;";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, apprenticeJobID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String apprenticeJob = resultSet.getString(1);
            String bildungsgangsleitung = resultSet.getString(2);
            String bereichsleitung = resultSet.getString(3);
            dataForCoversResult = new DataForCover(yearOfApprentice, apprenticeJob, bildungsgangsleitung, bereichsleitung);
        }

        return dataForCoversResult;
    }

    /**
     * Liest alle Lernfelder aus der Datenbank, abhängig vom Ausbildungsjahr und Ausbildungsberuf
     *
     * @param apprenticeJobID  Ausbildungsberuf ID
     * @param yearOfApprentice Ausbildungsjahr
     * @return
     * @throws SQLException
     */
    public List<Lernfeld> getLernfelder(int apprenticeJobID, int yearOfApprentice) throws SQLException {
        List<Lernfeld> lernfelderResults = new ArrayList<>();

        String sql = "SELECT lf.Bezeichnung, sd.Namedefault, lf.LFNR, lf.LFDauer, lf.`Start`, lf.Ende, f.Bezeichnung, lf.LFID FROM tbl_lernfeld AS lf " +
                "INNER JOIN tbl_schemadaten AS sd ON lf.LFVortitel = sd.SCHDID " +
                "INNER JOIN tbl_beruffach AS bf ON lf.ID_BerufFach = bf.BFID " +
                "INNER JOIN tbl_fach AS f ON bf.ID_Fach = f.FID " +
                "WHERE lf.ID_BerufFach IN " +
                "(SELECT bf.ID_Fach FROM tbl_uformberuf AS ufb " +
                "INNER JOIN tbl_beruffach AS bf ON ufb.UBID = bf.ID_UFormBeruf " +
                "WHERE ufb.ID_Beruf = ? AND ufb.UFBJahr = ?) AND " +
                "lf.Sichtbar = true " +
                "ORDER BY lf.ID_BerufFach";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, apprenticeJobID);
        preparedStatement.setInt(2, yearOfApprentice);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String description = resultSet.getString(1);
            String nameDefault = resultSet.getString(2);
            int lfNo = resultSet.getInt(3);
            int lfDuration = resultSet.getInt(4);
            int lfStart = resultSet.getInt(5);
            int lfEnd = resultSet.getInt(6);
            String fach = resultSet.getString(7);
            int lernfeldID = resultSet.getInt(8);

            lernfelderResults.add(new Lernfeld(getLernsituation(lernfeldID), description, nameDefault, lfNo, lfDuration, lfStart, lfEnd, fach, lernfeldID));
        }

        return lernfelderResults;
    }

    /**
     * Liest alle verfügbaren Lernfelder von einem Ausbildungsjahr und Ausbildungsberuf aus.
     *
     * @param apprenticeJob    Ausbildungsberuf
     * @param yearOfApprentice Ausbildungsjahr
     * @return
     * @throws SQLException
     * @deprecated Wurde durch eine Subquery in getLernfelder ersetzt.
     */
    @Deprecated
    public List<Integer> getAvailableLernfelder(int apprenticeJob, int yearOfApprentice) throws SQLException {
        List<Integer> availableLernfelderResults = new ArrayList<>();

        String sql = "SELECT bf.ID_Fach FROM tbl_uformberuf AS ufb " +
                "INNER JOIN tbl_beruffach AS bf ON ufb.UBID = bf.ID_UFormBeruf " +
                "WHERE ufb.ID_Beruf = ? AND ufb.UFBJahr = ?";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, apprenticeJob);
        preparedStatement.setInt(2, yearOfApprentice);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int lernfeld = resultSet.getInt(1);
            availableLernfelderResults.add(lernfeld);
        }

        return availableLernfelderResults;
    }

}
