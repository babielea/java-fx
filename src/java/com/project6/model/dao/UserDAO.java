package com.project6.model.dao;

import com.project6.database.Database;
import com.project6.model.domain.User;

import java.sql.*;

/**
 * Created by sulik.fabian on 21.06.2016.
 */
public class UserDAO {
    private Connection connection = Database.getInstance();
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private User user;

    public  UserDAO(User user) {
        this.user = user;
    }

    public boolean checkForLogin() {

        boolean pruefen = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT Username, Passwort FROM tbl_lehrer WHERE (Username=?)");
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
}
