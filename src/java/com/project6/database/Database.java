package com.project6.database;

import com.project6.model.domain.User;
import com.project6.model.domain.Username;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Baba on 20.06.2016.
 */
public class Database implements IDatabase {

    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "toor";
    private final String DB_HOST = "localhost:3306";

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Database() throws SQLException, ClassNotFoundException {
        Initialize();
    }

    public void Initialize() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/didakt?user=" + DB_USERNAME
                + "&password=" + DB_PASSWORD);
    }

    public Boolean Login(User user) {

        boolean pruefen = false;
        try {
            preparedStatement = connect.prepareStatement("SELECT Username, Passwort FROM tbl_lehrer WHERE (Username=?)");
            preparedStatement.setString(1, user.getUsernameRaw());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.getString("Username").equals(user.getPasswordRaw())) {
                System.out.println("Übereinstimmung");
                pruefen = true;
            } else {
                System.out.println("Falsch");
                pruefen = false;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return pruefen;
    }


    public Map<String, String> ergebnis() {

        try {
            connect.prepareStatement("");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        Map<String, String> stringStringMap = new HashMap<String, String>();
        stringStringMap.put("peter", "maffai");

        return stringStringMap;
    }

}
