package net.proselyte.crud.repository;

import net.proselyte.crud.model.Developer;
import java.sql.SQLException;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    void save(Developer developer) throws SQLException;

    @Override
    Developer getById(Long aLong) throws SQLException;

    @Override
    void deleteById(Long aLong) throws SQLException;

    @Override
    void getAll() throws SQLException;

    @Override
    void update(Long aLong) throws SQLException;
}
