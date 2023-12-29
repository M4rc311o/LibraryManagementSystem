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

    public List<UserSimpleView> getStandardUsersSimpleView() {
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
            List<UserSimpleView> userSimpleViews = new ArrayList<>();
            while (resultSet.next()) {
                userSimpleViews.add(mapToStandardUserSimpleView(resultSet));
            }
            return userSimpleViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("Standard users simple view could not be loaded.", e);
        }
    }

    public UserDeatiledView getUserDetailedViewById(Long id) {
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
                    throw new DataAccessException("Editing standard user has failed, no rows affected.");
                }
                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Editing standard user has failed, operation on database has failed.", e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Editing standard user has failed, operation on database has failed.", e);
        }
    }

    public void deleteUser(Long id) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM bds.user " +
                             "WHERE user_id = ?;"
             );
        ) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataAccessException("Deleting user account has failed, no rows affected");
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Deleting user account has failed", e);
        }
    }

    public void createUser(UserCreateView userCreateView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement createUserTablePreparedStatement = connection.prepareStatement(
                     "INSERT INTO bds.user (username, password_hash, first_name, last_name, role_id, date_of_birth) " +
                             "VALUES (?, ?, ?, ?, (SELECT role_id FROM bds.role WHERE role = ?), ?);", Statement.RETURN_GENERATED_KEYS
             );
        ) {
            createUserTablePreparedStatement.setString(1, userCreateView.getUsername());
            createUserTablePreparedStatement.setString(2, String.valueOf(userCreateView.getPassword()));
            createUserTablePreparedStatement.setString(3, userCreateView.getFistName());
            createUserTablePreparedStatement.setString(4, userCreateView.getLastName());
            createUserTablePreparedStatement.setString(5, userCreateView.getRole());
            createUserTablePreparedStatement.setDate(6, Date.valueOf(userCreateView.getDateOfBirth()));

            try {
                connection.setAutoCommit(false);
                int userTableAffectedColumns = createUserTablePreparedStatement.executeUpdate();
                if (userTableAffectedColumns == 0) {
                    connection.rollback();
                    throw new DataAccessException("Creating user has failed, no rows effected");
                }
                try (ResultSet generatedKeys = createUserTablePreparedStatement.getGeneratedKeys();
                     PreparedStatement createUserContactTablePreparedStatement = connection.prepareStatement(
                             "INSERT INTO bds.user_contact (user_id, phone_number, email) " +
                                     "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS
                     );
                ) {
                    if (generatedKeys.next()) {
                        createUserContactTablePreparedStatement.setLong(1, generatedKeys.getLong(1));
                        createUserContactTablePreparedStatement.setString(2, userCreateView.getPhoneNumber());
                        createUserContactTablePreparedStatement.setString(3, userCreateView.getEmail());
                        int userContactTableAffectedRows = createUserContactTablePreparedStatement.executeUpdate();
                        if (userContactTableAffectedRows == 0) {
                            connection.rollback();
                            throw new DataAccessException("Creating user has failed, no rows effected");
                        }
                    }
                }
                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Creating user has failed, operation on database has failed.", e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Creating user has failed, operation on database has failed.", e);
        }
    }

    public void editUser(UserEditView userEditView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement updateUserTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.user " +
                             "SET first_name = ?, last_name = ? " +
                             "WHERE user_id = ?;"
             );
             PreparedStatement updateUserContactTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.user_contact " +
                             "SET email = ?, phone_number = ? " +
                             "WHERE user_id = ?;"
             );
        ) {
            updateUserTablePreparedStatement.setString(1, userEditView.getFistName());
            updateUserTablePreparedStatement.setString(2, userEditView.getLastName());
            updateUserTablePreparedStatement.setLong(3, userEditView.getId());

            updateUserContactTablePreparedStatement.setString(1, userEditView.getEmail());
            updateUserContactTablePreparedStatement.setString(2, userEditView.getPhoneNumber());
            updateUserContactTablePreparedStatement.setLong(3, userEditView.getId());

            try {
                connection.setAutoCommit(false);
                int userTableAffectedRows = updateUserTablePreparedStatement.executeUpdate();
                int userContactTableAffectedRows = updateUserContactTablePreparedStatement.executeUpdate();
                if (userTableAffectedRows == 0 || userContactTableAffectedRows == 0) {
                    connection.rollback();
                    throw new DataAccessException("Editing user has failed, no rows affected.");
                }
                if (userEditView.getPassword() != null) {
                    try (PreparedStatement udpatePasswordPreparedStatement = connection.prepareStatement(
                            "UPDATE bds.user " +
                                    "SET password_hash = ? " +
                                    "WHERE user_id = ?;"
                            );
                    ) {
                        udpatePasswordPreparedStatement.setString(1, String.valueOf(userEditView.getPassword()));
                        udpatePasswordPreparedStatement.setLong(2, userEditView.getId());
                        int passwordAffectedColumns = udpatePasswordPreparedStatement.executeUpdate();
                        if (passwordAffectedColumns == 0) {
                            connection.rollback();
                            throw new DataAccessException("Editing user has failed, no rows affected.");
                        }
                    }
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

    private UserDeatiledView mapToStandardUserDetailedView(ResultSet rs) throws SQLException {
        UserDeatiledView standardUserDeatiledView = new UserDeatiledView();
        standardUserDeatiledView.setId(rs.getLong("user_id"));
        standardUserDeatiledView.setUsername(rs.getString("username"));
        standardUserDeatiledView.setRole(rs.getString("role"));
        standardUserDeatiledView.setFirstName(rs.getString("first_name"));
        standardUserDeatiledView.setLastName(rs.getString("last_name"));
        standardUserDeatiledView.setEmail(rs.getString("email"));
        standardUserDeatiledView.setPhoneNumber(rs.getString("phone_number"));
        standardUserDeatiledView.setDateOfBirth(rs.getDate("date_of_birth"));
        return standardUserDeatiledView;
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

    private UserSimpleView mapToStandardUserSimpleView(ResultSet rs) throws SQLException {
        UserSimpleView userSimpleView = new UserSimpleView();
        userSimpleView.setId(rs.getLong("user_id"));
        userSimpleView.setFirstName(rs.getString("first_name"));
        userSimpleView.setLastName(rs.getString("last_name"));
        userSimpleView.setRole(rs.getString("role"));
        return userSimpleView;
    }
}
