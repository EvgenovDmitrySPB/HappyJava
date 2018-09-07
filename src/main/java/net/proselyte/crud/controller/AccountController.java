package net.proselyte.crud.controller;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;

import java.sql.SQLException;

public class AccountController {
    private AccountRepository accountRepository;

    public AccountController() throws SQLException {
        accountRepository = new JDBCAccountRepository();
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
