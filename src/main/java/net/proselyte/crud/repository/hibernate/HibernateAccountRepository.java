package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Account;
import net.proselyte.crud.repository.AccountRepository;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class HibernateAccountRepository implements AccountRepository {
    public HibernateAccountRepository(SessionFactory sessionFactory) {

    }

    @Override
    public void save(Account account) throws SQLException {

    }

    @Override
    public Account getById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws SQLException {

    }

    @Override
    public void getAll() throws SQLException {

    }

    @Override
    public void update(Long aLong) throws SQLException {

    }
}
