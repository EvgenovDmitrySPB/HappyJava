package net.proselyte.crud.controller;

import net.proselyte.crud.builders.AccountBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.hibernate.HibernateAccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AccountController {
    private AccountRepository accountRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public AccountController(){
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                accountRepository = new JDBCAccountRepository(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                accountRepository = new HibernateAccountRepository(sessionFactory);
            }
        }
    }

    public void saveAccount(Map<String, Object> map){
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        String name = (String)map.get("name");

        AccountBuilder skillBuilder = new AccountBuilder();
        skillBuilder.withAccount(name);

        accountRepository.save(skillBuilder.toAccount());
    }

    public boolean getAccountById(Long id){
        if(id == 0){
            throw new IllegalArgumentException();
        }

        Account account = accountRepository.getById(id);
        if (account.getId() != null){
            System.out.println(account.toString());
            return true;
        }else {
            System.out.println("Account not found by id = " + id);
            return false;
        }
    }

    public void deleteById(Long id) {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        accountRepository.deleteById(id);
    }

    public void getAll() {

        List<Account> list = accountRepository.getAll();
        for (Account account:list) {
            System.out.println(account.toString());
        }
    }

    public void updateAccount(Map<String, Object> map) {
        if(map.isEmpty()){
            throw new IllegalArgumentException();
        }
        Long id     = (Long)map.get("id");
        String accountData = (String)map.get("accountData");

        AccountBuilder skillBuilder = new AccountBuilder();
        skillBuilder.withId(id).withAccount(accountData);

        accountRepository.update(skillBuilder.toAccount());
    }
}
