package net.proselyte.crud.repository;

import net.proselyte.crud.model.Skill;

import java.util.List;


public interface SkillRepository extends GenericRepository<Skill, Long >{
    @Override
    void save(Skill skill);

    @Override
    Skill getById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    List<Skill> getAll();

    @Override
    void update(Skill skill);
}
