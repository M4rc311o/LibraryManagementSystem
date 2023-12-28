package org.but.feec.bds.data;

import org.but.feec.bds.api.PhysicalBookCreateView;
import org.but.feec.bds.api.PhysicalBookDetailedView;
import org.but.feec.bds.api.PhysicalBookSimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhysicalBookRepository {
    public PhysicalBookRepository() {}

    public Long createPhysicalBook(PhysicalBookCreateView physicalBookCreateView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO bds.physical_book (condition, book_id, library_id) " +
                             "VALUES (?, ?, ?);"
             );
        ) {
            preparedStatement.setString(1, physicalBookCreateView.getCondition());
            preparedStatement.setLong(2, physicalBookCreateView.getBookId());
            preparedStatement.setLong(3, physicalBookCreateView.getLibraryId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DataAccessException("Creating physical book has failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Creating physical book has failed, operation on database has failed.", e);
        }
        return null;
    }

    public List<PhysicalBookSimpleView> getPhysicalBooksSimpleView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT physical_book_id, isbn, title, (library_id IS NULL) loaned " +
                             "FROM bds.physical_book pb " +
                             "JOIN bds.book b " +
                             "ON pb.book_id = b.book_id;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<PhysicalBookSimpleView> physicalBookSimpleViews = new ArrayList<>();
            while (resultSet.next()) {
                physicalBookSimpleViews.add(mapToPhysicalBookSimpleView(resultSet));
            }
            return physicalBookSimpleViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("Physical books simple view could not be loaded.", e);
        }
    }

    public PhysicalBookDetailedView getPhysicalBookDetailedViewById(Long id) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT pb.physical_book_id, title, isbn, name library_name, (pb.library_id IS NULL) loaned, issue_date, due_date, condition " +
                             "FROM bds.physical_book pb " +
                             "JOIN bds.book b " +
                             "ON pb.book_id = b.book_id " +
                             "LEFT JOIN bds.library l " +
                             "ON pb.library_id = l.library_id " +
                             "LEFT JOIN ( " +
                                "SELECT physical_book_id, issue_date, due_date " +
                                    "FROM bds.loaned_book lb " +
                                    "WHERE is_returned = false " +
                             ") lb " +
                             "ON pb.physical_book_id = lb.physical_book_id " +
                             "WHERE pb.physical_book_id = ?;"
             );
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPhysicalBookDetailedView(resultSet);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Physical book detailed view could not be loaded.", e);
        }
        return null;
    }

    public Optional<Long> getPhysicalBookLibraryId(Long id) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT library_id " +
                             "FROM bds.physical_book " +
                             "WHERE physical_book_id = ?;"
             );
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long libraryId = resultSet.getLong("library_id");
                    if (resultSet.wasNull()) {
                        return Optional.empty();
                    }
                    return Optional.of(libraryId);
                }
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Physical book library id could not be loaded.", e);
        }
        return null;
    }

    private PhysicalBookDetailedView mapToPhysicalBookDetailedView(ResultSet rs) throws SQLException {
        PhysicalBookDetailedView physicalBookDetailedView = new PhysicalBookDetailedView();
        physicalBookDetailedView.setId(rs.getLong("physical_book_id"));
        physicalBookDetailedView.setTitle(rs.getString("title"));
        physicalBookDetailedView.setIsbn(rs.getString("isbn"));
        physicalBookDetailedView.setLibrary(rs.getString("library_name"));
        physicalBookDetailedView.setLoaned(rs.getBoolean("loaned"));
        physicalBookDetailedView.setIssueDate(rs.getDate("issue_date"));
        physicalBookDetailedView.setDueDate(rs.getDate("due_date"));
        physicalBookDetailedView.setCondition(rs.getString("condition"));
        return physicalBookDetailedView;
    }

    private PhysicalBookSimpleView mapToPhysicalBookSimpleView(ResultSet rs) throws SQLException {
        PhysicalBookSimpleView physicalBookSimpleView = new PhysicalBookSimpleView();
        physicalBookSimpleView.setId(rs.getLong("physical_book_id"));
        physicalBookSimpleView.setIsbn(rs.getString("isbn"));
        physicalBookSimpleView.setTitle(rs.getString("title"));
        physicalBookSimpleView.setLoaned(rs.getBoolean("loaned"));
        return physicalBookSimpleView;
    }
}
