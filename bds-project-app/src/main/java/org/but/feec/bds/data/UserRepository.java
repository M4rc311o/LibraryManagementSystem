package org.but.feec.bds.data;

import org.but.feec.bds.api.UserAuthView;
import org.but.feec.bds.api.UserSessionView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public UserRepository() {}

    public UserAuthView findUserAuthViewByUsername(String username) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT username, password_hash " +
                             "FROM bds.user u " +
                             "WHERE u.username = ?;"
             );
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserAuthView(resultSet);
                }

            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Finding user by username has failed.", e);
        }
        return null;
    }

    public UserSessionView findUserSessionViewByUsername(String username) {
        try (Connection connection = DataSourceConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT user_id, username, role " +
                            "FROM bds.user u " +
                            "JOIN bds.role r " +
                            "ON u.role_id = r.role_id " +
                            "WHERE u.username = ?;"
            );
        ) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToUserSessionView(resultSet);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Finding user by username has failed.", e);
        }
        return null;
    }

    private UserSessionView mapToUserSessionView(ResultSet rs) throws SQLException {
        UserSessionView userSessionView = new UserSessionView();
        userSessionView.setUserId(rs.getLong("user_id"));
        userSessionView.setRole(rs.getString("role"));
        userSessionView.setUsername(rs.getString("username"));
        return userSessionView;
    }

    private UserAuthView mapToUserAuthView(ResultSet rs) throws SQLException {
        UserAuthView userAuthView = new UserAuthView();
        userAuthView.setUsername(rs.getString("username"));
        userAuthView.setPassword(rs.getString("password_hash"));
        return userAuthView;
    }
}
