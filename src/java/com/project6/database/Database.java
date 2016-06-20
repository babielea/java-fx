package com.project6.database;

import com.project6.model.domain.User;

import java.sql.*;

/**
 * Created by RottsiK on 20.06.2016.
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
        return null;
    }
}