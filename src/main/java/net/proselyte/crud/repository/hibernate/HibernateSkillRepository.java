package net.proselyte.crud.repository.hibernate;

import net.proselyte.crud.model.Skill;
import net.proselyte.crud.repository.SkillRepository;
import org.hibernate.SessionFactory;

import java.sql.SQLException;

public class HibernateSkillRepository implements SkillRepository {
    public HibernateSkillRepository(SessionFactory sessionFactory) {
    }

    @Override
    public void save(Skill skill) throws SQLException {

    }

    @Override
    public Skill getById(Long aLong) throws SQLException {
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
