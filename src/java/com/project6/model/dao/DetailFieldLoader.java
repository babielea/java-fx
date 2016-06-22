package com.project6.model.dao;

import com.project6.database.Database;
import com.project6.model.domain.Abteilung;
import com.project6.model.domain.Ausbildungsberuf;

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
    private Map<String, List<Object>> berufeMap = new HashMap<>();
    private Map<String, List<Object>> abteilungMap = new HashMap<>();

    private Connection connection = Database.getInstance();

    public DetailFieldLoader() {
    }


    public Map<String, List<Object>> getBerufe() {

        berufeMap = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT b.Berufname, l.Username FROM tbl_beruf b JOIN tbl_lehrer l ON b.ID_BLeitung = l.LId;");
            resultSet = preparedStatement.executeQuery();

            List<Object> berufeResults = new ArrayList<>();
            while (resultSet.next()) {
                String ausbildungsberuf = resultSet.getString(0);
                String leiter = resultSet.getString(1);
                berufeResults.add(new Ausbildungsberuf(ausbildungsberuf, leiter));
            }

            berufeMap.put("Ausbildungsberufe", berufeResults);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return berufeMap;
    }


    public Map<String, List<Object>> getAbteilung() {

        abteilungMap = new HashMap<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT a.Abteilungsname, l.Lehrername FROM tbl_abteilung a JOIN tbl_lehrer l ON a.ID_Leiter = l.LId;");
            resultSet = preparedStatement.executeQuery();

            List<Object> abteilungResults = new ArrayList<>();
            while (resultSet.next()) {
                String abteilung = resultSet.getString(0);
                String leiter = resultSet.getString(1);
                abteilungResults.add(new Abteilung(abteilung, leiter));
            }
            abteilungMap.put("Abteilungen", abteilungResults);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return abteilungMap;
    }



}
