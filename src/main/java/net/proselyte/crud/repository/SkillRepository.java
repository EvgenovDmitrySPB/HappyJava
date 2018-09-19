package net.proselyte.crud.repository;

import net.proselyte.crud.model.Skill;

import java.sql.SQLException;

public interface SkillRepository extends GenericRepository<Skill, Long >{
    @Override
    void save(Skill skill) throws SQLException;

    @Override
    Skill getById(Long aLong) throws SQLException;

    @Override
    void deleteById(Long aLong) throws SQLException;

    @Override
    void getAll() throws SQLException;

    @Override
    void update(Long aLong) throws SQLException;
}
