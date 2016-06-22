package com.project6.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class Database {
    private static Connection connection = makeConnection();

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "toor";
    private static final String DB_HOST = "localhost:3306";

    public static Connection getInstance() {
        return connection;
    }

    private Database() {
    }

    /**
     * Erstellt eine Datenbankverbindung zur MySQL Datenbank
     * @return
     */
    private static Connection makeConnection()  {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/didakt?user=" + DB_USERNAME
                    + "&password=" + DB_PASSWORD + "&useSSL=false&serverTimezone=UTC");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
