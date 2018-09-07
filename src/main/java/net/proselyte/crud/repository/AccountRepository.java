package net.proselyte.crud.repository;

import net.proselyte.crud.model.Account;

import java.sql.SQLException;

public interface AccountRepository extends GenericRepository<Account, Long>{
    @Override
    void save(Account account) throws SQLException;

    @Override
    Account getById(Long aLong) throws SQLException;

    @Override
    void deleteById(Long aLong) throws SQLException;

    @Override
    void createTable() throws SQLException;

    @Override
    void getAll() throws SQLException;
}
