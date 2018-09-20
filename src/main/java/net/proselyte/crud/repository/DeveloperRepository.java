package net.proselyte.crud.repository;

import net.proselyte.crud.model.Developer;
import java.sql.SQLException;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    @Override
    void save(Developer developer);

    @Override
    Developer getById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void getAll();

    @Override
    void update(Developer developer);
}
