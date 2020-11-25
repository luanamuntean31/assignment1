package repository.account;

import model.Account;
import model.Client;
import model.Employee;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import model.validation.Notification;
import repository.client.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import java.util.Date;


public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;



    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Account account, Long clientId) {
        try {
            PreparedStatement insertAccountStatement = connection
                    .prepareStatement("INSERT INTO account values (null,?,?, ?, ?)",Statement.RETURN_GENERATED_KEYS);

            insertAccountStatement.setLong(1, clientId);
            insertAccountStatement.setString(2, account.getAccountType());
            insertAccountStatement.setLong(3, account.getAmount());
            insertAccountStatement.setDate(4, new java.sql.Date (account.getCreationDate().getTime()));
            insertAccountStatement.executeUpdate();

            ResultSet rs = insertAccountStatement.getGeneratedKeys();
            rs.next();
            long nrAcc = rs.getLong(1);
            account.setAccountNumber(nrAcc);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Account account, Long nrAccount) {
        try {
            PreparedStatement updateStatement = connection
                    .prepareStatement("UPDATE account SET typeAccount = ?, amount=?, creationDate=?\n" +
                            "WHERE nrAccount = ?");
            updateStatement.setString(1, account.getAccountType());
            updateStatement.setLong(2, account.getAmount());
            updateStatement.setDate(3, new java.sql.Date (account.getCreationDate().getTime()));
            updateStatement.setLong(4, nrAccount);

            updateStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Notification<Account> viewAccount(Long nrAccount) {
        Notification<Account> findByTypeNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM  account  WHERE nrAccount=\'" + nrAccount + "\'";
            ResultSet accountResultSet = statement.executeQuery(sql);

            //ia primul rez din cele 5 conturi
            if (accountResultSet.next()) {
                Account account = new AccountBuilder()
                        .setAccountNumber(accountResultSet.getLong("nrAccount"))
                        .setAccountType(accountResultSet.getString("typeAccount"))
                        .setAmount(accountResultSet.getLong("amount"))
                        .setCreationDate(accountResultSet.getDate("creationDate"))
                        .build();

                findByTypeNotification.setResult(account);
                return findByTypeNotification;

            } else {
                findByTypeNotification.addError("Invalid type!");
                return findByTypeNotification;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findByTypeNotification;
    }

    @Override
    public boolean delete( Notification<Account> account, Long nrAccount) {
        try {
            PreparedStatement deleteStatement = connection
                    .prepareStatement("DELETE FROM account WHERE nrAccount = ?");
            deleteStatement.setLong(1, nrAccount);

            deleteStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            //verif incepand cu primul elem
            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }




    private Account getAccountFromResultSet(ResultSet rs) throws SQLException{
        return new AccountBuilder()
                .setAccountNumber(rs.getLong("nrAccount"))
                .setAccountType(rs.getString("typeAccount"))
                .setAmount(rs.getLong("amount"))
                .setCreationDate(new Date(rs.getDate("creationDate").getTime()))
                .build();
    }


}

