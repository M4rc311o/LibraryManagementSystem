package org.but.feec.bds.data;

import org.but.feec.bds.api.BookCreateView;
import org.but.feec.bds.api.BookDetailedView;
import org.but.feec.bds.api.BookSimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.*;
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

    public BookDetailedView getBookDetailedViewByIsbn(String isbn) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT book_id, isbn, title, year_of_publication, evaluation, genre, language, book_binding, literary_period " +
                             "FROM bds.book b " +
                             "JOIN bds.genre g " +
                             "ON b.genre_id = g.genre_id " +
                             "JOIN bds.language l " +
                             "ON b.language_id = l.language_id " +
                             "JOIN bds.book_binding bb " +
                             "ON b.book_binding_id = bb.book_binding_id " +
                             "JOIN bds.literary_period lp " +
                             "ON b.literary_period_id = lp.literary_period_id " +
                             "WHERE isbn = ?;"
             );
        ) {
            preparedStatement.setString(1, isbn);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToBookDetailedView(resultSet);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Finding book by isbn has failed.", e);
        }
        return null;
    }

    public Long createBook(BookCreateView bookCreateView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO bds.book (ISBN, title, year_of_publication, evaluation, genre_id, language_id, book_binding_id, literary_period_id) " +
                             "VALUES (?, ?, ?, ?, " +
                                "(SELECT genre_id FROM bds.genre WHERE genre = ?), " +
                                "(SELECT language_id FROM bds.language WHERE language = ?), " +
                                "(SELECT book_binding_id FROM bds.book_binding WHERE book_binding = ?), " +
                                "(SELECT literary_period_id FROM bds.literary_period WHERE literary_period = ?));", Statement.RETURN_GENERATED_KEYS
             );
        ) {
            preparedStatement.setString(1, bookCreateView.getIsbn());
            preparedStatement.setString(2, bookCreateView.getTitle());
            preparedStatement.setInt(3, bookCreateView.getYear());
            preparedStatement.setBigDecimal(4, bookCreateView.getEvaluation());
            preparedStatement.setString(5, bookCreateView.getGenre());
            preparedStatement.setString(6, bookCreateView.getLanguage());
            preparedStatement.setString(7, bookCreateView.getBinding());
            preparedStatement.setString(8, bookCreateView.getLiteraryPeriod());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataAccessException("Creating book has failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Creating book has failed, operation on database has failed.", e);
        }
        return null;
    }

    public List<String> getBookGenres() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT genre " +
                             "FROM bds.genre;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<String> bookGenres = new ArrayList<>();
            while (resultSet.next()) {
                bookGenres.add(resultSet.getString("genre"));
            }
            return bookGenres;
        }
        catch (SQLException e) {
            throw new DataAccessException("Book genres could not be loaded.", e);
        }
    }

    public List<String> getBookLanguages() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT language " +
                             "FROM bds.language;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<String> bookLanguages = new ArrayList<>();
            while (resultSet.next()) {
                bookLanguages.add(resultSet.getString("language"));
            }
            return bookLanguages;
        }
        catch (SQLException e) {
            throw new DataAccessException("Book languages could not be loaded.", e);
        }
    }

    public List<String> getBookBindings() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT book_binding " +
                             "FROM bds.book_binding;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<String> bookBindings = new ArrayList<>();
            while (resultSet.next()) {
                bookBindings.add(resultSet.getString("book_binding"));
            }
            return bookBindings;
        }
        catch (SQLException e) {
            throw new DataAccessException("Book bindings could not be loaded.", e);
        }
    }

    public List<String> getBookLiteraryPeriods() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT literary_period " +
                             "FROM bds.literary_period;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<String> bookLiteraryPeriods = new ArrayList<>();
            while (resultSet.next()) {
                bookLiteraryPeriods.add(resultSet.getString("literary_period"));
            }
            return bookLiteraryPeriods;
        }
        catch (SQLException e) {
            throw new DataAccessException("Book literary periods could not be loaded.", e);
        }
    }

    private BookDetailedView mapToBookDetailedView(ResultSet rs) throws SQLException {
        BookDetailedView bookDetailedView = new BookDetailedView();
        bookDetailedView.setId(rs.getLong("book_id"));
        bookDetailedView.setIsbn(rs.getString("isbn"));
        bookDetailedView.setTitle(rs.getString("title"));
        bookDetailedView.setYear(rs.getInt("year_of_publication"));
        bookDetailedView.setEvaluation(rs.getFloat("evaluation"));
        bookDetailedView.setGenre(rs.getString("genre"));
        bookDetailedView.setLanguage(rs.getString("language"));
        bookDetailedView.setBinding(rs.getString("book_binding"));
        bookDetailedView.setLiteraryPeriod(rs.getString("literary_period"));
        return bookDetailedView;
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
