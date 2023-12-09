package org.but.feec.bds.data;

import org.but.feec.bds.api.BookRequestCreateView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BookRequestRepository {
    public BookRequestRepository() {}

    public void createBookRequest(BookRequestCreateView bookRequestCreateView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO bds.book_request (title, isbn, user_id) " +
                          "VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS
             );
        ) {
            preparedStatement.setString(1, bookRequestCreateView.getTitle());
            preparedStatement.setString(2, bookRequestCreateView.getIsbn());
            preparedStatement.setLong(3, bookRequestCreateView.getUserId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataAccessException("Creating book request failed, no rows affected.");
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Creating book request failed.");
        }
    }
}
