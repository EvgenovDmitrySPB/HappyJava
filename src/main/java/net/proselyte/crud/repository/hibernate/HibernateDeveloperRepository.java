package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Developer;
import net.proselyte.crud.repository.DeveloperRepository;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class HibernateDeveloperRepository implements DeveloperRepository {
    public HibernateDeveloperRepository(SessionFactory sessionFactory) {
    }

    @Override
    public void save(Developer developer) throws SQLException {

    }

    @Override
    public Developer getById(Long aLong) throws SQLException {
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
