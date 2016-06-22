package com.project6.database;

import com.project6.config.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class Database {
    private static Connection connection = makeConnection();

    private static String DB_USERNAME;
    private static String DB_PASSWORD;
    private static String DB_HOST;
    private static String DB_NAME;

    private Database() {

    }

    public static Connection getInstance() {
        return connection;
    }
    /**
     * Erstellt eine Datenbankverbindung zur MySQL Datenbank
     * @return connection
     */
    private static Connection makeConnection() {
        DB_USERNAME = ConfigLoader.getUsername();
        DB_PASSWORD = ConfigLoader.getPassword();
        DB_HOST = ConfigLoader.getHostname() + ":" + ConfigLoader.getPort();
        DB_NAME = ConfigLoader.getDbname();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?user=" + DB_USERNAME
                    + "&password=" + DB_PASSWORD + "&serverTimezone=UTC");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
