package org.but.feec.bds.data;

import org.but.feec.bds.api.UserAuthView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public UserRepository() {}

    public UserAuthView findUserByUsername(String username) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT username, password_hash " +
                             "FROM bds.user u " +
                             "WHERE u.username = ?;")
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserAuth(resultSet);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Finding user by username has failed.", e);
        }
        return null;
    }

    private UserAuthView mapToUserAuth(ResultSet rs) throws SQLException {
        UserAuthView userAuthView = new UserAuthView();
        userAuthView.setUsername(rs.getString("username"));
        userAuthView.setPassword(rs.getString("password_hash"));
        return userAuthView;
    }
}
