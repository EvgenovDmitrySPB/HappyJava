package net.proselyte.crud.controller;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
import net.proselyte.crud.util.ConnectorMySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountController {
    private AccountRepository accountRepository;
    private Connection connection;

    public AccountController() throws SQLException {
        this.connection = new ConnectorMySQL().getConnection();
        if (this.connection == null){
            System.out.println("Warning! You don't have connection with MySQL");
            return;
        }else {
            accountRepository = new JDBCAccountRepository(connection);
        }
    }

    public void createTable() throws SQLException {
        accountRepository.createTable();
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
