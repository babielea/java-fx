package com.project6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class Database {
    private static Connection connection = makeConnection();

    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "";
    private static String DB_HOST = "localhost:3306";

    public static Connection getInstance() {
        return connection;
    }

    private Database() {
    }

    private static Connection makeConnection()  {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/didakt?user=" + DB_USERNAME
                    + "&password=" + DB_PASSWORD+"&serverTimezone=UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
