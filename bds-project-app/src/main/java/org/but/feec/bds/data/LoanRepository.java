package org.but.feec.bds.data;

import org.but.feec.bds.api.LoanBookCreateView;
import org.but.feec.bds.api.LoanSimpleView;
import org.but.feec.bds.api.ReturnBook;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    public LoanRepository() {}

    public List<LoanSimpleView> getLoansSimpleViewForUser(long userId) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT loaned_book_id, title, issue_date, due_date, is_returned " +
                             "FROM bds.loaned_book lb " +
                             "LEFT JOIN bds.physical_book pb " +
                             "ON lb.physical_book_id = pb.physical_book_id " +
                             "LEFT JOIN bds.book b " +
                             "ON pb.book_id = b.book_id " +
                             "WHERE user_id = ?;"
             );
        ) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<LoanSimpleView> loanSimpleViews = new ArrayList<>();
                while (resultSet.next()) {
                    loanSimpleViews.add(mapToLoanSimpleView(resultSet));
                }
                return loanSimpleViews;
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Loans simple view for user could not be loaded.", e);
        }
    }

    public void returnBook(ReturnBook returnBook) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement updateLoanedBookTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.loaned_book " +
                             "SET is_returned = true, fee = ? " +
                             "WHERE physical_book_id = ? AND is_returned = false;", Statement.RETURN_GENERATED_KEYS
             );
             PreparedStatement updatePhysicalBookTablePreparedStatement = connection.prepareStatement(
                     "UPDATE bds.physical_book " +
                             "SET library_id = ? " +
                             "WHERE physical_book_id = ?;", Statement.RETURN_GENERATED_KEYS
             );
        ) {
            updateLoanedBookTablePreparedStatement.setBigDecimal(1, returnBook.getFee());
            updateLoanedBookTablePreparedStatement.setLong(2, returnBook.getId());

            updatePhysicalBookTablePreparedStatement.setLong(1, returnBook.getLibraryId());
            updatePhysicalBookTablePreparedStatement.setLong(2, returnBook.getId());
            try {
                connection.setAutoCommit(false);
                int loanedBookTableAffectedRows = updateLoanedBookTablePreparedStatement.executeUpdate();
                int physicalBookTableAffectedRows = updatePhysicalBookTablePreparedStatement.executeUpdate();

                if (loanedBookTableAffectedRows == 0 || physicalBookTableAffectedRows == 0) {
                    connection.rollback();
                    throw new DataAccessException("Returning book has failed, no rows affected.");
                }

                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Returning book has failed, operation on database has failed.", e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Returning book has failed, operation on database has failed.", e);
        }
    }

    public void createLoan(LoanBookCreateView loanBookCreateView) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement createLoanedBookPreparedStatement = connection.prepareStatement(
                     "INSERT INTO bds.loaned_book (user_id, physical_book_id, issue_date, due_date, library_id) " +
                             "VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS
             );
             PreparedStatement updatePhysicalBookLibraryPreparedStatement = connection.prepareStatement(
                     "UPDATE bds.physical_book " +
                             "SET library_id = NULL " +
                             "WHERE physical_book_id = ?;"
             );
        ) {
            createLoanedBookPreparedStatement.setLong(1, loanBookCreateView.getUserId());
            createLoanedBookPreparedStatement.setLong(2, loanBookCreateView.getPhysicalBookId());
            createLoanedBookPreparedStatement.setDate(3, Date.valueOf(loanBookCreateView.getIssueDate()));
            createLoanedBookPreparedStatement.setDate(4, Date.valueOf(loanBookCreateView.getDueDate()));
            createLoanedBookPreparedStatement.setLong(5, loanBookCreateView.getLibrary());

            updatePhysicalBookLibraryPreparedStatement.setLong(1, loanBookCreateView.getPhysicalBookId());
            try {
                connection.setAutoCommit(false);
                int loanedBookTableAffectedRows = createLoanedBookPreparedStatement.executeUpdate();
                int physicalBookTableAffectedRows = updatePhysicalBookLibraryPreparedStatement.executeUpdate();

                if (loanedBookTableAffectedRows == 0 || physicalBookTableAffectedRows == 0) {
                    connection.rollback();
                    throw new DataAccessException("Creating book loan has failed, no rows affected.");
                }

                connection.commit();
            }
            catch (SQLException e) {
                connection.rollback();
                throw new DataAccessException("Creating book loan has failed, operation on database has failed.", e);
            }
            finally {
                connection.setAutoCommit(true);
            }
        }
        catch (SQLException e) {
            throw new DataAccessException("Creating book loan has failed, operation on database has failed.", e);
        }
    }

    private LoanSimpleView mapToLoanSimpleView(ResultSet rs) throws SQLException {
        LoanSimpleView loanSimpleView = new LoanSimpleView();
        loanSimpleView.setId(rs.getLong("loaned_book_id"));
        loanSimpleView.setBookName(rs.getString("title"));
        loanSimpleView.setIssueDate(rs.getDate("issue_date"));
        loanSimpleView.setDueDate(rs.getDate("due_date"));
        loanSimpleView.setReturned(rs.getBoolean("is_returned"));
        return loanSimpleView;
    }
}
