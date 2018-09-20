package net.proselyte.crud.repository;

import net.proselyte.crud.model.Account;

import java.sql.SQLException;

public interface AccountRepository extends GenericRepository<Account, Long>{
    @Override
    void save(Account account);

    @Override
    Account getById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void getAll();

    @Override
    void update(Account account);
}
