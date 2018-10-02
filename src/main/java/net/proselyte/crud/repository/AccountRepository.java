package net.proselyte.crud.repository;

import net.proselyte.crud.model.Account;

import java.util.List;

public interface AccountRepository extends GenericRepository<Account, Long>{
    @Override
    void save(Account account);

    @Override
    Account getById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<Account> getAll();

    @Override
    void update(Account account);
}
