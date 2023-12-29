package org.but.feec.bds.data;

import org.but.feec.bds.api.SqlInjectionStaffView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlInjectionStaffRepository {
    public List<SqlInjectionStaffView> getSqlInjectionStaffView(String username, String password) {
        try (Connection connection = DataSourceConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT staff_id, username, join_date, phone_number " +
                             "FROM bds.unsecure_staff " +
                             "WHERE username = '" + username + "' AND password = '" + password + "';"
             );
        ){
            List<SqlInjectionStaffView> sqlInjectionStaffViews = new ArrayList<>();
            while (resultSet.next()) {
                sqlInjectionStaffViews.add(mapToSqlInjectionStaffView(resultSet));
            }
            return sqlInjectionStaffViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("SQL injection staff view could not be loaded.", e);
        }
    }

    private SqlInjectionStaffView mapToSqlInjectionStaffView(ResultSet rs) throws SQLException {
        SqlInjectionStaffView sqlInjectionStaffView = new SqlInjectionStaffView();
        sqlInjectionStaffView.setId(rs.getLong("staff_id"));
        sqlInjectionStaffView.setUsername(rs.getString("username"));
        sqlInjectionStaffView.setJoinDate(rs.getDate("join_date"));
        sqlInjectionStaffView.setPhoneNumber(rs.getString("phone_number"));
        return sqlInjectionStaffView;
    }
}
