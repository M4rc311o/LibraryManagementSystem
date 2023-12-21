package org.but.feec.bds.data;

import org.but.feec.bds.api.LoanSimpleView;
import org.but.feec.bds.config.DataSourceConfig;
import org.but.feec.bds.exceptions.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
