package com.project6.database;

import com.project6.model.domain.User;

import java.sql.SQLException;

/**
 * Created by RottsiK on 20.06.2016.
 */
public interface IDatabase {

    void Initialize() throws ClassNotFoundException, SQLException;
    Boolean Login(User user);

}
