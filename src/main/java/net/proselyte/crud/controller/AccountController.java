package net.proselyte.crud.controller;

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

    public void saveAccount(Account Account){
        if(Account == null){
            throw new IllegalArgumentException();
        }
        accountRepository.save(Account);
    }

    public Account getAccountById(Long id){
        if(id == 0){
            throw new IllegalArgumentException();
        }
        return accountRepository.getById(id);
    }

    public void deleteById(Long id) {
        if(id == 0){
            throw new IllegalArgumentException();
        }
        accountRepository.deleteById(id);
    }

    public void getAll() {

        accountRepository.getAll();
    }

    public void updateAccount(Account account) {
        accountRepository.update(account);
    }
}
