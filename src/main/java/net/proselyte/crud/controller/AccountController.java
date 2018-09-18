package net.proselyte.crud.controller;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountController {
    private AccountRepository accountRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public AccountController() throws SQLException {
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();
            if (this.connection == null){
                System.out.println("Warning! You don't have connection with MySQL");
                return;
            }else {
                accountRepository = new JDBCAccountRepository(connection);
            }

        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();

        }

    }

    public void saveAccount(Account Account) throws SQLException {
        if(Account == null){
            throw new IllegalArgumentException();
        }
        accountRepository.save(Account);
    }

    public Account getAccountById(Long id) throws SQLException {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        return accountRepository.getById(id);
    }

    public void getAll() throws SQLException {

        accountRepository.getAll();
    }
}
