package org.but.feec.bds.data;

import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public BookRepository() {}

    public List<BookSimpleView> getBooksSimpleView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT title, isbn, year_of_publication, evaluation, language " +
                             "FROM bds.book b " +
                             "LEFT JOIN bds.language l " +
                             "ON b.language_id = l.language_id;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<BookSimpleView> bookSimpleViews = new ArrayList<>();
            while (resultSet.next()) {
                bookSimpleViews.add(mapToBookSimpleView(resultSet));
            }
            return bookSimpleViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("Books simple view could not be loaded.", e);
        }
    }

    private BookSimpleView mapToBookSimpleView(ResultSet rs) throws SQLException {
        BookSimpleView bookSimpleView = new BookSimpleView();
        bookSimpleView.setTitle(rs.getString("title"));
        bookSimpleView.setIsbn(rs.getString("isbn"));
        bookSimpleView.setYear(rs.getInt("year_of_publication"));
        bookSimpleView.setLanguage(rs.getString("language"));
        bookSimpleView.setEvaluation(rs.getFloat("evaluation"));
        return bookSimpleView;
    }
}
