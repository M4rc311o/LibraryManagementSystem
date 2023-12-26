package org.but.feec.bds.data;

import org.but.feec.bds.api.*;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<StandardUserSimpleView> getStandardUsersView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT user_id, first_name, last_name, role " +
                             "FROM bds.user u " +
                             "JOIN bds.role r " +
                             "ON u.role_id = r.role_id " +
                             "WHERE role IN ('basic', 'child', 'student');"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<StandardUserSimpleView> standardUserSimpleViews = new ArrayList<>();
            while (resultSet.next()) {
                standardUserSimpleViews.add(mapToStandardUserSimpleView(resultSet));
            }
            return standardUserSimpleViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("Standard users simple view could not be loaded.", e);
        }
    }

    public StandardUserDetailedView getStandardUserDetailedViewById(Long id) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.user_id, username, date_of_birth, first_name, last_name, role, email, phone_number " +
                             "FROM bds.user u " +
                             "JOIN bds.role r " +
                             "ON u.role_id = r.role_id " +
                             "LEFT JOIN bds.user_contact uc " +
                             "ON u.user_id = uc.user_id " +
                             "WHERE u.user_id = ?;"
             );
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToStandardUserDetailedView(resultSet);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Standard user detailed view could not be loaded.", e);
        }
        return null;
    }

    public void editStandardUser(StandardUserEditView standardUserEditView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement updateUserTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.user " +
                             "SET first_name = ?, last_name = ?, role_id = (SELECT role_id FROM bds.role WHERE role = ?), date_of_birth = ? " +
                             "WHERE user_id = ?;"
             );
             PreparedStatement updateUserContactTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.user_contact " +
                             "SET email = ?, phone_number = ? " +
                             "WHERE user_id = ?;"
             );
        ) {
            updateUserTablePreparedStatement.setString(1, standardUserEditView.getFirstName());
            updateUserTablePreparedStatement.setString(2, standardUserEditView.getLastName());
            updateUserTablePreparedStatement.setString(3, standardUserEditView.getRole());
            updateUserTablePreparedStatement.setDate(4, Date.valueOf(standardUserEditView.getDateOfBirth()));
            updateUserTablePreparedStatement.setLong(5, standardUserEditView.getId());

            updateUserContactTablePreparedStatement.setString(1, standardUserEditView.getEmail());
            updateUserContactTablePreparedStatement.setString(2, standardUserEditView.getPhoneNumber());
            updateUserContactTablePreparedStatement.setLong(3, standardUserEditView.getId());

            try {
                connection.setAutoCommit(false);
                int userTableAffectedRows = updateUserTablePreparedStatement.executeUpdate();
                int userContactTableAffectedRows = updateUserContactTablePreparedStatement.executeUpdate();
                if (userTableAffectedRows == 0 || userContactTableAffectedRows == 0) {
                    connection.rollback();
                    throw new DataAccessException("Editing user has failed, no rows affected.");
                }
                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Editing user has failed, operation on database has failed.", e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Editing user has failed, operation on database has failed.", e);
        }
    }

    private StandardUserDetailedView mapToStandardUserDetailedView(ResultSet rs) throws SQLException {
        StandardUserDetailedView standardUserDetailedView = new StandardUserDetailedView();
        standardUserDetailedView.setId(rs.getLong("user_id"));
        standardUserDetailedView.setUsername(rs.getString("username"));
        standardUserDetailedView.setRole(rs.getString("role"));
        standardUserDetailedView.setFirstName(rs.getString("first_name"));
        standardUserDetailedView.setLastName(rs.getString("last_name"));
        standardUserDetailedView.setEmail(rs.getString("email"));
        standardUserDetailedView.setPhoneNumber(rs.getString("phone_number"));
        standardUserDetailedView.setDateOfBirth(rs.getDate("date_of_birth"));
        return standardUserDetailedView;
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

    private StandardUserSimpleView mapToStandardUserSimpleView(ResultSet rs) throws SQLException {
        StandardUserSimpleView standardUserSimpleView = new StandardUserSimpleView();
        standardUserSimpleView.setId(rs.getLong("user_id"));
        standardUserSimpleView.setFirstName(rs.getString("first_name"));
        standardUserSimpleView.setLastName(rs.getString("last_name"));
        standardUserSimpleView.setRole(rs.getString("role"));
        return standardUserSimpleView;
    }
}
