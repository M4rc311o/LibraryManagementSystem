package org.but.feec.bds.data;

import org.but.feec.bds.api.PhysicalBookSimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhysicalBookRepository {
    public PhysicalBookRepository() {}

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

    private PhysicalBookSimpleView mapToPhysicalBookSimpleView(ResultSet rs) throws SQLException {
        PhysicalBookSimpleView physicalBookSimpleView = new PhysicalBookSimpleView();
        physicalBookSimpleView.setId(rs.getLong("physical_book_id"));
        physicalBookSimpleView.setIsbn(rs.getString("isbn"));
        physicalBookSimpleView.setTitle(rs.getString("title"));
        physicalBookSimpleView.setLoaned(rs.getBoolean("loaned"));
        return physicalBookSimpleView;
    }
}
