package com.project6.model.dao;

import com.project6.database.Database;
import com.project6.model.domain.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public UserDAO(User user) {
        this.user = user;
    }

    public boolean checkForLogin() {

        try {
            preparedStatement = connection.prepareStatement("SELECT Username, Passwort FROM tbl_lehrer WHERE (Username=?)");
            preparedStatement.setString(1, user.getUsernameRaw());

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) return false;

            String md5Password = hashString(user.getPasswordRaw());
            return resultSet.getString(2).equals(md5Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String hashString(String message) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ignored) {

        }
        return "";
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
