package com.project6.model.dao;

import com.project6.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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


    public Map<String, String> ergebnis(String beruf, String jahr) {

        Map<String, String> datenMap = new HashMap<String, String>();

        try {

            resultSet = preparedStatement.executeQuery();




        } catch (SQLException e) {
            e.printStackTrace();
        }





        return datenMap;
    }
}
