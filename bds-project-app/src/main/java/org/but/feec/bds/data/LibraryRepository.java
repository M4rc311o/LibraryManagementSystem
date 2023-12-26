package org.but.feec.bds.data;

import org.but.feec.bds.api.LibraryNameWithIdIdentifier;
import org.but.feec.bds.api.LibrarySimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryRepository {
    public LibraryRepository() {}

    public List<LibrarySimpleView> getLibrariesSimpleView() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT library_id, name, country, city, street, house_num " +
                             "FROM bds.library l " +
                             "LEFT JOIN bds.address a " +
                             "ON l.address_id = a.address_id " +
                             "LEFT JOIN bds.city ci " +
                             "ON a.city_id = ci.city_id " +
                             "LEFT JOIN bds.country co " +
                             "ON a.country_id = co.country_id;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<LibrarySimpleView> librarySimpleViews = new ArrayList<>();
            while (resultSet.next()) {
                librarySimpleViews.add(mapToLibrarySimpleView(resultSet));
            }
            return librarySimpleViews;
        }
        catch (SQLException e) {
            throw new DataAccessException("Libraries simple view could not be loaded.", e);
        }
    }

    public List<LibraryNameWithIdIdentifier> getLibrariesNamesWithIdIdentifier() {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT library_id, name " +
                             "FROM bds.library;"
             );
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<LibraryNameWithIdIdentifier> libraryNamesWithIdIdentifiers = new ArrayList<>();
            while (resultSet.next()) {
                libraryNamesWithIdIdentifiers.add(mapToLibraryNamesWithIdIdentifiers(resultSet));
            }
            return libraryNamesWithIdIdentifiers;
        }
        catch (SQLException e) {
            throw new DataAccessException("Libraries names with id identifiers could not be loaded.", e);
        }
    }

    private LibraryNameWithIdIdentifier mapToLibraryNamesWithIdIdentifiers(ResultSet rs) throws SQLException {
        LibraryNameWithIdIdentifier libraryNameWithIdIdentifier = new LibraryNameWithIdIdentifier();
        libraryNameWithIdIdentifier.setId(rs.getLong("library_id"));
        libraryNameWithIdIdentifier.setName(rs.getString("name"));
        return libraryNameWithIdIdentifier;
    }

    private LibrarySimpleView mapToLibrarySimpleView(ResultSet rs) throws SQLException {
        LibrarySimpleView librarySimpleView = new LibrarySimpleView();
        librarySimpleView.setId(rs.getLong("library_id"));
        librarySimpleView.setName(rs.getString("name"));
        librarySimpleView.setCountry(rs.getString("country"));
        librarySimpleView.setCity(rs.getString("city"));
        librarySimpleView.setStreet(rs.getString("street"));
        librarySimpleView.setHouseNumber(rs.getInt("house_num"));
        return librarySimpleView;
    }
}
