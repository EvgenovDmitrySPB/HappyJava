package net.proselyte.crud.repository.jdbc;

import net.proselyte.crud.builders.AccountBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.repository.AccountRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAccountRepository implements AccountRepository {
    private Connection connection;
    private Statement statement;

    public JDBCAccountRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Account account) throws SQLException {
            try {
                statement = connection.createStatement();
                String getSql = "INSERT INTO ACCOUNTS VALUES(" + account.getId().intValue() + ",'" + account.getAccountData() + "')";
                statement.executeUpdate(getSql);
                System.out.println("Operation save ACCOUNTS. Ok");
            }catch (SQLException e){
                System.out.println("Operation save ACCOUNTS. SQLException");
            }finally{
                if (statement != null){
                    statement.close();
                }
            }
    }

    @Override
    public Account getById(Long aLong) throws SQLException {
            try {
            statement = connection.createStatement();
            String getSql = "SELECT id,accountData FROM ACCOUNTS WHERE id=" + aLong.intValue();
            ResultSet result = statement.executeQuery(getSql);
            AccountBuilder accountBuilder = new AccountBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String accountData = result.getString(2);
                accountBuilder.withId(id).withAccount(accountData);
            }
            Account account = accountBuilder.toAccount();
            return account;
        } catch (SQLException e) {
            System.out.println("Operation getById ACCOUNTS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {
        try {
            statement = connection.createStatement();
            String getSql = "DELETE FROM ACCOUNTS WHERE id = " + aLong.intValue();
            statement.executeUpdate(getSql);
            System.out.println("Operation delete ACCOUNTS. Ok");
        }catch (SQLException e){
            System.out.println("Operation delete ACCOUNTS. SQLException");
        }finally{
            if (statement != null){
                statement.close();
            }
        }
    }

    @Override
    public void getAll() throws SQLException {
        int temp = 0;
        try {
            statement = connection.createStatement();
            String getSql = "SELECT id,accountData FROM ACCOUNTS";
            ResultSet result = statement.executeQuery(getSql);
            AccountBuilder accountBuilder = new AccountBuilder();
            while (result.next()) {
                Long id = result.getLong(1);
                String accountData = result.getString(2);
                accountBuilder.withId(id).withAccount(accountData);
                Account account = accountBuilder.toAccount();
                System.out.println(account.toString());
                temp++;
            }
            if (temp ==0){
                System.out.println("0 element's in ACCOUNTS ");
            }
        } catch (SQLException e) {
            System.out.println("Operation getAll ACCOUNTS . SQLException");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
