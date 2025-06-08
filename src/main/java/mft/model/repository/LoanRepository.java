package mft.model.repository;

import mft.model.entity.Loan;
import mft.tools.ConnectionProvider;
import mft.tools.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public LoanRepository() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    public void save(Loan loan) throws SQLException {
        preparedStatement = connection.prepareStatement("select loan_seq.nextval from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        loan.setId(resultSet.getInt("nextval"));

        preparedStatement = connection.prepareStatement("insert into loans (id, person_id, book_id)values (?, ?, ?)");
        preparedStatement.setInt(1, loan.getId());
        preparedStatement.setInt(2, loan.getPerson().getId());
        preparedStatement.setInt(3, loan.getBook().getId());
        preparedStatement.execute();
    }

    public void edit(Loan loan) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "update loans set person_id=?, book_id=?, loan_date=?, return_date=? where id=?"
        );
        preparedStatement.setInt(1, loan.getPerson().getId());
        preparedStatement.setInt(2, loan.getBook().getId());
        preparedStatement.setDate(3, loan.getLoanDate() == null? null: Date.valueOf(loan.getLoanDate()));
        preparedStatement.setDate(4, loan.getReturnDate() == null? null:Date.valueOf(loan.getReturnDate()));
        preparedStatement.setInt(5, loan.getId());
        preparedStatement.execute();
    }

    public void returnLoan(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "update loans set return_date=sysdate where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void delete(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "delete from loans where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Loan> findAll() throws SQLException {
        List<Loan> loanList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from loan_report");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            loanList.add(EntityMapper.loanMapper(resultSet));
        }
        return loanList;
    }

    public Loan findById(int id) throws SQLException {
        Loan loan = null;
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from loan_report where loan_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            loan = EntityMapper.loanMapper(resultSet);
        }
        return loan;
    }

    public List<Loan> findByPersonNameAndFamily(String name, String family) throws SQLException {
        List<Loan> loanList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from loan_report where person_name like ? and person_family like ?");
        preparedStatement.setString(1, name + "%");
        preparedStatement.setString(2, family + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Loan loan = EntityMapper.loanMapper(resultSet);
            loanList.add(loan);
        }
        return loanList;
    }

    public List<Loan> findByBookTitle(String title) throws SQLException {
        List<Loan> loanList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from loan_report where book_title like ?");
        preparedStatement.setString(1, title + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Loan loan = EntityMapper.loanMapper(resultSet);
            loanList.add(loan);
        }
        return loanList;
    }

    public List<Loan> findNotReturnedLoans() throws SQLException {
        List<Loan> loanList = new ArrayList<>();
        connection = ConnectionProvider.getConnectionProvider().getConnection();
        preparedStatement = connection.prepareStatement("select * from loan_report where return_date is null");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            loanList.add(EntityMapper.loanMapper(resultSet));
        }
        return loanList;
    }


    @Override
    public void close() throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        connection.close();
    }
}
